package uz.toshshahartransxizmat.toshbustravel.ui.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.PayOrderEntity
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.ui.payment.component.CardExpiryInput
import uz.toshshahartransxizmat.toshbustravel.ui.payment.component.CardNumberInput
import uz.toshshahartransxizmat.toshbustravel.ui.payment.viewModel.PaymentViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class PaymentScreen(
    private val orderId:Int,
    private val amount: Long
): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var cardNumber by remember { mutableStateOf("") }
        var cardExpiryDate by remember { mutableStateOf("") }
        val isButtonEnabled = cardNumber.length == 16 && cardExpiryDate.length == 4
        var showErrorDialog by remember { mutableStateOf(true) }

        val viewModel = rememberKoinInject<PaymentViewModel>()
        val state = viewModel.state.collectAsState()

        Scaffold{
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                PageHeader(
                    type = PageHeaderType.Heading(text = getStrings("payment")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onNavigationClick = { navigator.pop() }
                )

                CardNumberInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    title = getStrings("card_number"),
                    onCardNumberChange = { newCardNumber ->
                        cardNumber = newCardNumber
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                CardExpiryInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    title = getStrings("card_expiry"),
                    onCardExpiryChange = { newCardExpiryDate ->
                        cardExpiryDate = newCardExpiryDate
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getStrings("amount_due"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "$amount UZS",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = blueA220,
                    )
                }

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue(getStrings("pay")),
                    size = ButtonSize.Large,
                    enabled = isButtonEnabled,
                    loading = state.value.isLoading,
                    onClick = {
                        val payOrderEntity = PayOrderEntity(
                            orderId = orderId,
                            cardNumber = cardNumber,
                            cardExpiryDate = cardExpiryDate,
                            transactionId = amount.toInt()
                        )
                        viewModel.loadPayment(payOrderEntity)
                    }
                )
            }

            if (state.value.error.isNotBlank()) {
                ErrorDialog(
                    errorMessage = state.value.error,
                    showDialog = showErrorDialog,
                    onDismiss = { showErrorDialog = false }
                )
            }

            if (state.value.isLoaded){
                println("sentOtp--->${state.value.payment.sentOtp}")
            }
        }
    }
}