package uz.toshshahartransxizmat.toshbustravel.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
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
import uz.toshshahartransxizmat.toshbustravel.components.otp.OtpConfirmationScreen
import uz.toshshahartransxizmat.toshbustravel.components.otp.OtpType
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.share.Platform
import uz.toshshahartransxizmat.toshbustravel.share.getSettingsSource
import uz.toshshahartransxizmat.toshbustravel.share.provideDeviceId
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputPasswordComponent
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputPhone
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth
import uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel.AuthViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.home.HomeScreen
import uz.toshshahartransxizmat.toshbustravel.util.ACCESS_TOKEN_KEY
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class LogInScreen(
    private val languageCode:String
): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var phoneNumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf(TextFieldValue("")) }
        val isPhoneNumberValid = phoneNumber.length == 9
        val settings = remember { getSettingsSource() }
        val viewModel = rememberKoinInject<AuthViewModel>()
        val state = viewModel.state.collectAsState()
        val platformName = remember { Platform() }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PageHeader(
                type = PageHeaderType.Heading(text = getStrings("login")),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                onNavigationClick = { navigator.pop() }
            )

            InputPhone(
                modifier = Modifier
                    .fillMaxWidth(),
                title = getStrings("phone_number"),
                onPhoneNumberChange = { phoneNumber = it }
            )

            InputPasswordComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = getStrings("password"),
                password = password,
                onPasswordChange = { password = it }
            )

            TextAuth(
                modifier = Modifier
                .fillMaxWidth(),
                text = getStrings("no_account"),
                textClick = getStrings("register"),
                navigator = {
                    navigator.push(AuthScreen(languageCode = languageCode))
                }
            )
            Text(
                modifier = Modifier.clickable {
                        navigator.push(ForgotPasswordScreen())
                    },
                text = TextValue(getStrings("forgot_password")),
                color = Color(0xFF007AFF),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(weight = 1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 56.dp),
                text = TextValue(getStrings("continue")),
                size = ButtonSize.Large,
                enabled = isPhoneNumberValid && password.text.length >= 4,
                loading = state.value.isLoading,
                onClick = {
                    val signInEntity = SignInEntity(
                        username = "998$phoneNumber",
                        password = password.text,
                        platform = platformName.name,
                        code = "",
                        hash = "",
                        deviceId = provideDeviceId(),
                        version = "string",
                        lang = languageCode,
                        deviceName = platformName.name,
                        ipAddress = "string"
                    )
                    viewModel.loadLoginIn(signInEntity)
                }
            )
        }

        if (state.value.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Text(text = state.value.error, fontSize = 25.sp)
            }
        }

        if (state.value.isLoaded){
            if (state.value.success.data.sentOtp){
                val hashLogIn = state.value.success.data.hash
                if (hashLogIn!=null){
                    navigator.push(
                        OtpConfirmationScreen(
                            userName = "998$phoneNumber",
                            password = password.text,
                            hash = hashLogIn,
                            deviceId = provideDeviceId(),
                            languageCode = languageCode,
                            otpType = OtpType.SIGN_IN
                        )
                    )
                }else{
                    println("hash is null")
                }

            }else{
                state.value.success.data.accessToken?.let { settings.saveValue(ACCESS_TOKEN_KEY, it) }
                navigator.push(HomeScreen())
            }
        }
    }

}