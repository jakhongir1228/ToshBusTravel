package uz.toshshahartransxizmat.toshbustravel.ui.auth.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.Icon
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.theme.errorLight
import uz.toshshahartransxizmat.toshbustravel.theme.gray650
import uz.toshshahartransxizmat.toshbustravel.theme.grayA220
import uz.toshshahartransxizmat.toshbustravel.theme.white100
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

@Composable
internal fun InputPasswordComponent(
    title:String,
    modifier: Modifier = Modifier,
    password: TextFieldValue,
    onPasswordChange: (TextFieldValue) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val borderColor = if (password.text.length < 4 && password.text.isNotEmpty()) errorLight else Color(0xFFD0D5DD)

    Column(
        modifier = modifier
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = TextValue(text = title),
            color = gray650
        )
        Box {
            BasicTextField(
                value = password,
                onValueChange = onPasswordChange,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp, end =16.dp)
                            .border(
                                border = BorderStroke(1.dp, borderColor),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp)
                            .height(34.dp)
                    ) {
                        if (password.text.isEmpty()) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = TextValue(getStrings("enter_password")),
                                color = grayA220
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                innerTextField()
                            }
                            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(icon = IconValue(image))
                            }
                        }
                    }
                }
            )
        }
        if (password.text.length < 4 && password.text.isNotEmpty()) {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                text = TextValue(getStrings("enter_at_least_4_characters")),
                color = errorLight,
                fontSize = 12.sp
            )
        }
    }
}
