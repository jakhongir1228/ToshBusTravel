package uz.toshshahartransxizmat.toshbustravel.components.otp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.components.otp.viewModel.OtpViewModel
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.theme.black100
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.theme.gray650
import uz.toshshahartransxizmat.toshbustravel.theme.red500
import uz.toshshahartransxizmat.toshbustravel.ui.auth.LogInScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth
import uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel.AuthViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class OtpConfirmationScreen(
    private val userName:String,
    private val password:String,
    private val hash:String,
    private val deviceId:String,
    private val languageCode:String
): Screen {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val vmAuth = rememberKoinInject<AuthViewModel>()
        val stateAuth = vmAuth.state.collectAsState()
        val viewModel = rememberKoinInject<OtpViewModel>()
        val state = viewModel.state.collectAsState()

        val keyboardController = LocalSoftwareKeyboardController.current

        keyboardController?.apply {
            if (state.value.isTimerCompleted) hide() else show()
        }

        LaunchedEffect(Unit) {
            viewModel.startTimer()
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
                color = gray650,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = TextValue("+$userName"),
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
                isError = state.value.errorText != null
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
                    .padding(bottom = 56.dp),
                text = TextValue("Продолжить"),
                size = ButtonSize.Large,
                enabled = state.value.isInputCompleted,
                loading = stateAuth.value.isLoading,
                onClick = {
                    val signUpEntity = SignUpEntity(
                        username = userName,
                        password = password,
                        code = state.value.value,
                        hash = hash,
                        deviceId = deviceId
                    )

                    vmAuth.loadAuth(signUpEntity)
                }
            )
        }

        if (stateAuth.value.error.isNotBlank()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Text(text = stateAuth.value.error, fontSize = 25.sp)
            }
        }

        if (stateAuth.value.isLoaded){
            navigator.push(LogInScreen(languageCode = languageCode))
        }
    }
}