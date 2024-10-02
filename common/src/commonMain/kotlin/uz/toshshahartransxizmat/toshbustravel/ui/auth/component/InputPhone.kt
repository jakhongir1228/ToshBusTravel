package uz.toshshahartransxizmat.toshbustravel.ui.auth.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.theme.borderColor200
import uz.toshshahartransxizmat.toshbustravel.theme.grayA220
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun InputPhone(
    title: String,
    modifier: Modifier = Modifier,
    onPhoneNumberChange: (String) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    val borderColor = borderColor200

    Column(
        modifier = modifier
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = TextValue(text = title),
            color = MaterialTheme.colorScheme.tertiary
        )
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end =16.dp)
                .border(
                    border = BorderStroke(1.dp, borderColor),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
                .height(34.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(24.dp),
                painter = painterResource("drawable/uz_phone.png"),
                contentDescription = "Uzbekistan Flag",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "+998",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                value = phoneNumber,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.scrim),
                onValueChange = { newText ->
                    if (newText.replace(" ", "").length <= 9) {
                        phoneNumber = newText.filter { it.isDigit() }
                        onPhoneNumberChange(phoneNumber)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PhoneNumberTransformation(),
                decorationBox = { innerTextField ->
                    if (phoneNumber.isEmpty()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = getStrings("enter_phone_number"),
                            color = grayA220
                        )
                    }
                    innerTextField()
                },
                cursorBrush = SolidColor(value = MaterialTheme.colorScheme.scrim)
            )
        }

    }
}


