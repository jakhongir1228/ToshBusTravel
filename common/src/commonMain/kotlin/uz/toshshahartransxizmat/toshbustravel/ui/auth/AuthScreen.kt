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
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInput
import uz.toshshahartransxizmat.toshbustravel.components.otp.OtpConfirmationScreen
import uz.toshshahartransxizmat.toshbustravel.components.otp.OtpType
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.share.provideDeviceId
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputConfirmPassword
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputPhone
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth
import uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel.AuthViewModel
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class AuthScreen(
    private val languageCode:String
): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberKoinInject<AuthViewModel>()
        val state = viewModel.state.collectAsState()
        var showErrorDialog by remember { mutableStateOf(true) }

        var phoneNumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf(false) }
        val isPhoneNumberValid = phoneNumber.length == 9
        val isPasswordValid = password.length >= 4
        val isPasswordsMatch = password == confirmPassword

        val isFormValid = isPhoneNumberValid && isPasswordValid && isPasswordsMatch

        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageHeader(
                    type = PageHeaderType.Heading(text = getStrings("register")),
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

                InputConfirmPassword(
                    title = getStrings("password"),
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    isError = passwordError && password != confirmPassword
                )

                InputConfirmPassword(
                    title = getStrings("confirm_password"),
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    isError = passwordError && password != confirmPassword
                )

                TextAuth(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = getStrings("have_account"),
                    textClick = getStrings("sign_in"),
                    navigator = {
                        navigator.pop()
                    }
                )

                Spacer(modifier = Modifier.weight(weight = 1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 56.dp),
                    text = TextValue(getStrings("continue")),
                    size = ButtonSize.Large,
                    enabled = isFormValid,
                    loading = state.value.isLoading,
                    onClick = {
                        passwordError = password != confirmPassword
                        if (!passwordError && password.length >= 4) {
                            val signUpEntity = SignUpEntity(
                                username = "998$phoneNumber",
                                password = password,
                                code = "",
                                hash = "",
                                deviceId = provideDeviceId()
                            )
                            viewModel.loadAuth(signUpEntity)
                            showErrorDialog = true
                        }

                    }
                )
            }
        }

        if (state.value.error.isNotBlank()) {
            ErrorDialog(
                errorMessage = state.value.error,
                showDialog = showErrorDialog,
                onDismiss = { showErrorDialog = false }
            )
        }

        if (state.value.isLoaded){
            val hashSignUp = state.value.success.data.hash
            if (hashSignUp!=null){
                navigator.push(
                    OtpConfirmationScreen(
                        userName = "998$phoneNumber",
                        password = password,
                        hash = hashSignUp,
                        deviceId = provideDeviceId(),
                        languageCode = languageCode,
                        otpType = OtpType.SIGN_UP
                    )
                )
            }
            else{
                println("hash is null")
            }
        }

    }
}
