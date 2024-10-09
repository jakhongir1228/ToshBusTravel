package uz.toshshahartransxizmat.toshbustravel.ui.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInput
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.AddCardEntity
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.CardMessageComponent
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard.CardCvvInput
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard.CardExpiryInput
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard.CardNumberInput
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard.CardTypeSelector
import uz.toshshahartransxizmat.toshbustravel.ui.card.viewModel.CardViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class AddCardScreen: Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var cardNumber by remember { mutableStateOf("") }
        var cardExpiryDate by remember { mutableStateOf("") }
        var cardCvv by remember { mutableStateOf("") }
        var fullName by remember { mutableStateOf("") }
        var showErrorDialog by remember { mutableStateOf(true) }
        var isCardType by remember { mutableStateOf(true) }
        val viewModel = rememberKoinInject<CardViewModel>()
        val state = viewModel.state.collectAsState()

        val isButtonEnabled = if (isCardType) {
            cardNumber.length == 16 && cardExpiryDate.length == 4
        } else {
            cardNumber.length == 16 &&
                    cardExpiryDate.length == 4 &&
                    cardCvv.length == 3 &&
                    fullName.isNotBlank()
        }

        Scaffold{
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                PageHeader(
                    type = PageHeaderType.Heading(text = getStrings("add_cards")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onNavigationClick = { navigator.pop() }
                )

                CardTypeSelector(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    onOptionSelected = { cardType ->
                        isCardType = cardType
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (!isCardType){
                    CardMessageComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        text = getStrings("note"),
                        iconMessage = painterResource(res = "drawable/iconNone.xml")
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

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

                if (!isCardType) {
                    Spacer(modifier = Modifier.height(16.dp))

                    CardCvvInput(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        title = "CVV",
                        onCardCvvChange = { newCvv->
                            cardCvv = newCvv
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextInput(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = TextValue(getStrings("full_name")),
                        keyboardOptions = KeyboardOptions.Default,
                        keyboardActions = KeyboardActions(),
                        placeholder = TextValue(getStrings("enter_full_name"))
                    )
                }

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue(getStrings("add_cards")),
                    size = ButtonSize.Large,
                    enabled = isButtonEnabled,
                    loading = state.value.isLoading,
                    onClick = {
                        if (isCardType){
                            val addCardEntity = AddCardEntity(
                                cardNumber = cardNumber,
                                expiryDate = cardExpiryDate
                            )
                            viewModel.loadAddCard(addCardEntity)
                            showErrorDialog = true
                        }
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
                navigator.push(
                    ChooseCardScreen()
                )
            }
        }
    }
}