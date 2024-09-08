package uz.toshshahartransxizmat.toshbustravel.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import uz.toshshahartransxizmat.toshbustravel.components.otp.OtpConfirmationScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.InputPhone
import uz.toshshahartransxizmat.toshbustravel.ui.auth.component.TextAuth
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class ForgotPasswordScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var phoneNumber by remember { mutableStateOf("") }
        val isPhoneNumberValid = phoneNumber.length == 9

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PageHeader(
                type = PageHeaderType.Heading(text = getStrings("forgot_password")),
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
                enabled = isPhoneNumberValid,
                onClick = {
                  // navigator.push(OtpConfirmationScreen())
                }
            )
        }
    }

}
