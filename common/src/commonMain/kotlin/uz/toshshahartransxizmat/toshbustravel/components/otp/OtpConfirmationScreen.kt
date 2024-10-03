package uz.toshshahartransxizmat.toshbustravel.components.otp

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import uz.toshshahartransxizmat.toshbustravel.components.otp.viewModel.OtpViewModel
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.share.Platform
import uz.toshshahartransxizmat.toshbustravel.share.isImeVisible
import uz.toshshahartransxizmat.toshbustravel.share.provideDeviceId
import uz.toshshahartransxizmat.toshbustravel.theme.black100
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.theme.red500
import uz.toshshahartransxizmat.toshbustravel.ui.auth.LogInScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.NewPasswordScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth
import uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel.AuthViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class OtpConfirmationScreen(
    private val userName:String,
    private val password:String,
    private val hash:String,
    private val deviceId:String,
    private val languageCode:String,
    private val otpType: OtpType
): Screen {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val vmAuth = rememberKoinInject<AuthViewModel>()
        val stateAuth = vmAuth.state.collectAsState()
        val viewModel = rememberKoinInject<OtpViewModel>()
        val state = viewModel.state.collectAsState()
        val platformName = remember { Platform() }
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
                color = MaterialTheme.colorScheme.tertiary,
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
                isError = stateAuth.value.error != ""
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
                loading = stateAuth.value.isLoading,
                onClick = {
                    when(otpType){
                        OtpType.SIGN_UP-> {
                            val signUpEntity = SignUpEntity(
                                username = userName,
                                password = password,
                                code = state.value.value,
                                hash = hash,
                                deviceId = deviceId
                            )
                            vmAuth.loadAuth(signUpEntity)
                            showErrorDialog = true
                        }
                        OtpType.SIGN_IN-> {
                            val signInEntity = SignInEntity(
                                username = userName,
                                password = password,
                                platform = platformName.name,
                                code = state.value.value,
                                hash = hash,
                                deviceId = deviceId,
                                version = "string",
                                lang = languageCode,
                                deviceName = platformName.name,
                                ipAddress = "string"
                            )
                            vmAuth.loadLoginIn(signInEntity)
                            showErrorDialog = true
                        }
                        OtpType.RESET_PASSWORD-> {
                            val resetEntity = ResetEntity(
                                username = userName,
                                newPassword = "",
                                code = state.value.value,
                                hash = hash,
                                deviceId = provideDeviceId()
                            )
                            vmAuth.loadResetPassword(resetEntity)
                            showErrorDialog = true
                        }
                    }

                }
            )
        }

        if (stateAuth.value.error.isNotBlank()){
            ErrorDialog(
                errorMessage = stateAuth.value.error,
                showDialog = showErrorDialog,
                onDismiss = { showErrorDialog = false }
            )
        }

        if (stateAuth.value.isLoaded){
            if (stateAuth.value.successReset.completed){
                navigator.push(NewPasswordScreen(
                    username = userName,
                    hash = hash,
                    languageCode = languageCode
                ))
            }else{
                navigator.push(LogInScreen(languageCode = languageCode))
            }
        }
    }
}