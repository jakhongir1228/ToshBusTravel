package uz.toshshahartransxizmat.toshbustravel.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInput
import uz.toshshahartransxizmat.toshbustravel.components.otp.OtpConfirmationScreen
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.share.provideDeviceId
import uz.toshshahartransxizmat.toshbustravel.theme.errorLight
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputConfirmPassword
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputPhone
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth
import uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel.AuthViewModel

internal class AuthScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberKoinInject<AuthViewModel>()
        val state = viewModel.state.collectAsState()

        var firstName by remember { mutableStateOf("") }
        var showError by remember { mutableStateOf(false) }
        var phoneNumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf(false) }
        val isPhoneNumberValid = phoneNumber.length == 9

        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageHeader(
                    type = PageHeaderType.Heading(text = "Регистрация"),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onNavigationClick = { navigator.pop() }
                )

                TextInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = TextValue("ФИО"),
                    isError = showError && firstName.isEmpty(),
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions(),
                    placeholder = TextValue("Введите ФИО")
                )

                InputPhone(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = "Номер телефона",
                    onPhoneNumberChange = { phoneNumber = it }
                )

                InputConfirmPassword(
                    title = "Пароль",
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    isError = passwordError && password != confirmPassword
                )

                InputConfirmPassword(
                    title = "Подтвердите пароль",
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    isError = passwordError && password != confirmPassword
                )

                TextAuth(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "У вас есть аккаунт?",
                    textClick = "Войти",
                    navigator = navigator
                )

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue("Продолжить"),
                    size = ButtonSize.Large,
                    enabled = true,
                    loading = state.value.isLoading,
                    onClick = {
//                        showError = firstName.isEmpty()
//                        passwordError = password != confirmPassword
//                        if (!showError && !passwordError && password.length >= 8) {
//                           // navigator.push(ForgotPasswordScreen())
//
//                        }
                        val signUpEntity = SignUpEntity(
                            username = "string1fg234",
                            password = "string22",
                            code = "",
                            hash = "",
                            deviceId = provideDeviceId()
                        )
                        viewModel.loadAuth(signUpEntity)
                    }
                )
            }
        }

        if (state.value.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Text(text = state.value.error, fontSize = 25.sp)
            }
        }

        if (state.value.isLoaded){
            navigator.push(OtpConfirmationScreen(
                userName = "string1fg234",
                password = "string22",
                code = state.value.success.code,
                hash = state.value.success.hash,
                deviceId = provideDeviceId()
            ))
        }

    }
}