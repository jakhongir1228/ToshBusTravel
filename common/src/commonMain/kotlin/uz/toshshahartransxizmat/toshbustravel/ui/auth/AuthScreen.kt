package uz.toshshahartransxizmat.toshbustravel.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInput
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputPasswordComponent
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputPhone
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth

internal class AuthScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var firstName by remember { mutableStateOf("") }
        var showError by remember { mutableStateOf(false) }
        var phoneNumber by remember { mutableStateOf("") }
        val isPhoneNumberValid = phoneNumber.length == 9

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
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

            InputPasswordComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Пароль"
            )

            InputPasswordComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Подтвердите пароль"
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
                enabled = isPhoneNumberValid,
                onClick = {
                    showError = firstName.isEmpty()
                    if (!showError) {
                        navigator.push(ForgotPasswordScreen())
                    }
                }
            )
        }
    }

}