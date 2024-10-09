package uz.toshshahartransxizmat.toshbustravel.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.CardBottomSheet
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.CardMessageComponent
import uz.toshshahartransxizmat.toshbustravel.ui.card.component.ChooseCardComponent
import uz.toshshahartransxizmat.toshbustravel.ui.card.viewModel.CardViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class ChooseCardScreen: Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showDialog by remember { mutableStateOf(false) }
        val viewModel = rememberKoinInject<CardViewModel>()
        val state = viewModel.state.collectAsState()
        var showErrorDialog by remember { mutableStateOf(true) }

        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                PageHeader(
                    type = PageHeaderType.Heading(text = getStrings("choose_card")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onNavigationClick = { navigator.pop() }
                )
                CardMessageComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    text = getStrings("active_card_tip"),
                    iconMessage = painterResource(res = "drawable/giveMoneyIcon.png")
                )

//                Spacer(modifier = Modifier.height(16.dp))
//
//                MyCardComponent(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp, end = 16.dp)
//                )

                Spacer(modifier = Modifier.height(16.dp))

                ChooseCardComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    text = getStrings("choose_card")
                ){
                    showDialog = true
                    viewModel.loadGetCards()
                    showErrorDialog = true
                }

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue(getStrings("continue")),
                    size = ButtonSize.Large,
                    enabled = false,
                    onClick = {

                    }
                )
            }

            if (showDialog) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.32f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            showDialog = false
                        }
                )
            }

            if (showDialog) {
                val cardList = state.value.cards

                CardBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = getStrings("choose_card"),
                    cardList = cardList,
                    onDismissRequest = {
                        showDialog = false
                    },
                    onAddCardClick = {
                        showDialog = false
                        navigator.push(
                            AddCardScreen()
                        )
                    }
                ){ card->
                    println("card---> ${card}")
                }
            }
            if (state.value.error.isNotBlank()) {
                ErrorDialog(
                    errorMessage = state.value.error,
                    showDialog = showErrorDialog,
                    onDismiss = { showErrorDialog = false }
                )
            }
//            if (state.value.isLoaded){
//                navigator.push(
//                    ChooseCardScreen()
//                )
//            }
        }
    }
}