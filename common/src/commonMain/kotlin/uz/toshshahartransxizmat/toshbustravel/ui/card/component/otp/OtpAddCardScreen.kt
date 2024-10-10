package uz.toshshahartransxizmat.toshbustravel.ui.card.component.otp

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.components.otp.OtpInputItem
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.VerifyCardEntity
import uz.toshshahartransxizmat.toshbustravel.share.isImeVisible
import uz.toshshahartransxizmat.toshbustravel.theme.black100
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.theme.red500
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth
import uz.toshshahartransxizmat.toshbustravel.ui.card.ChooseCardScreen
import uz.toshshahartransxizmat.toshbustravel.ui.card.viewModel.CardViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class OtpAddCardScreen(
    private val cardToken: String,
    private val smsNotificationNumber:String
): Screen {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberKoinInject<OtpAddCardViewModel>()
        val state = viewModel.state.collectAsState()
        val cardVM = rememberKoinInject<CardViewModel>()
        val cardState = cardVM.state.collectAsState()

        var showErrorDialog by remember { mutableStateOf(true) }

        val keyboardController = LocalSoftwareKeyboardController.current

        val keyboardVisible = isImeVisible()
        val buttonBottomPadding by animateDpAsState(
            targetValue = if (keyboardVisible) 252.dp else 56.dp,
            animationSpec = tween(durationMillis = 100)
        )

        keyboardController?.apply {
            //  if (state.value.isTimerCompleted) hide() else show()
        }

        LaunchedEffect(Unit) {
            viewModel.startTimer()
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PageHeader(
                type = PageHeaderType.Heading(text = getStrings("sms_verification")),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                onNavigationClick = {
                    navigator.pop()
                }
            )

            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = TextValue(getStrings("sent_to_number")),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = TextValue("+$smsNotificationNumber"),
                color = black100,
                fontSize = 16.sp
            )

            OtpInputItem(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 55.dp),
                text = viewModel.state.value.value,
                length = state.value.requiredValueLength,
                onTextChanged = { value, _ ->
                    viewModel.onOtpValueChanged(value)
                },
                enabled = true,
                isError = cardState.value.error != ""
            )

            state.value.errorText?.let { text ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    text = text,
                    color = red500,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp, top = 24.dp, end = 52.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                if (state.value.isTimerCompleted) {
                    TextAuth(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "",
                        textClick = getStrings("resend"),
                        navigator = {
                            viewModel.resendOtp()
                        }
                    )
                } else {
                    state.value.timerText?.let { value ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(1f),
                            textAlign = TextAlign.Center,
                            text = value,
                            color = blueA220,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(weight = 1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = buttonBottomPadding),
                text = TextValue("Продолжить"),
                size = ButtonSize.Large,
                enabled = state.value.isInputCompleted,
                loading = cardState.value.isLoading,
                onClick = {
                    val verifyCardEntity = VerifyCardEntity(
                        cardToken = cardToken,
                        code = state.value.value
                    )
                    cardVM.loadVerifyCard(verifyCardEntity)
                    showErrorDialog = true
                }
            )
        }
        if (cardState.value.error.isNotBlank()) {
            ErrorDialog(
                errorMessage = cardState.value.error,
                showDialog = showErrorDialog,
                onDismiss = { showErrorDialog = false }
            )
        }
        if (cardState.value.isLoaded){
            navigator.push(
                ChooseCardScreen()
            )
        }
    }
}