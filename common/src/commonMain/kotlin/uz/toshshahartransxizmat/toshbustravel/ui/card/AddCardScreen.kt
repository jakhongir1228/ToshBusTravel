package uz.toshshahartransxizmat.toshbustravel.ui.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard.CardExpiryInput
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard.CardNumberInput
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class AddCardScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var cardNumber by remember { mutableStateOf("") }
        var cardExpiryDate by remember { mutableStateOf("") }
        val isButtonEnabled = cardNumber.length == 16 && cardExpiryDate.length == 4
        var showErrorDialog by remember { mutableStateOf(true) }


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

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue(getStrings("add_cards")),
                    size = ButtonSize.Large,
                    enabled = isButtonEnabled,
                    onClick = {

                    }
                )
            }
        }
    }
}