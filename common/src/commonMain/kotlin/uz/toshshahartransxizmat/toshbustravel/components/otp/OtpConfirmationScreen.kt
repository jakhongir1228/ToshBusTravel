package uz.toshshahartransxizmat.toshbustravel.components.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.components.navigator.BottomItem
import uz.toshshahartransxizmat.toshbustravel.components.otp.state.OtpConfirmationState
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.theme.black100
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.theme.gray150
import uz.toshshahartransxizmat.toshbustravel.theme.gray650
import uz.toshshahartransxizmat.toshbustravel.theme.red500
import uz.toshshahartransxizmat.toshbustravel.theme.white100
import uz.toshshahartransxizmat.toshbustravel.ui.auth.ForgotPasswordScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.LogInScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel.AuthViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.home.HomeScreen
import uz.toshshahartransxizmat.toshbustravel.ui.home.HomeTab
import uz.toshshahartransxizmat.toshbustravel.ui.orders.OrdersTab
import uz.toshshahartransxizmat.toshbustravel.ui.profile.ProfileTab

internal class OtpConfirmationScreen(
    private val userName:String,
    private val password:String,
    private val code:String,
    private val hash:String,
    private val deviceId:String
): Screen {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberKoinInject<AuthViewModel>()
        val stateAuth = viewModel.state.collectAsState()

        val state = OtpConfirmationState(
            phoneNumber = userName,
            requiredTimeout = 2,
            requiredValueLength = code.length
        )
        val keyboardController = LocalSoftwareKeyboardController.current

        keyboardController?.apply {
           // if (state.isTimerCompleted) hide() else show()
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PageHeader(
                type = PageHeaderType.Heading(text = "СМС-подтверждение"),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                onNavigationClick = {
                    navigator.pop()
                }
            )

            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = TextValue("Отправили на номер"),
                color = gray650,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = TextValue(state.phoneNumber), //state.phoneNumber
                color = black100,
                fontSize = 16.sp
            )

            OtpInputItem(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 55.dp),
                text = code,
                length = state.requiredValueLength,
                onTextChanged = { value, _ ->
                    //accept(Intent.OtpValueChanged(value))
                                },
                enabled = state.isInputEnabled,
                isError = state.errorText != null
            )

            state.errorText?.let { text ->
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
                state.timerText?.let { value ->
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

                if (state.isResendAvailable) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        text = TextValue("Отправить еще раз"),
                        size = ButtonSize.Medium,
                        type = ButtonType.Primary,
                        onClick = {
                           // accept(Intent.ResendOtpValue(Commands.RequestOtp))
                        }
                    )
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
                enabled = true,
                loading = stateAuth.value.isLoading,
                onClick = {
                   // viewModel.loadAuth(signUpEntity)
                }
            )
        }

        if (stateAuth.value.error.isNotBlank()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Text(text = stateAuth.value.error, fontSize = 25.sp)
            }
        }

        if (stateAuth.value.isLoaded){
            navigator.push(LogInScreen())
        }
    }

}