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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InputConfirmPassword(
    title: String,
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val borderColor = if (isError) errorLight else if (value.length < 4 && value.isNotEmpty()) errorLight else Color(0xFFD0D5DD)

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = TextValue(title),
            color = gray650
        )
        Box {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .border(
                        border = BorderStroke(1.dp, borderColor),
                        shape = RoundedCornerShape(12.dp)
                    ),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon = IconValue(image), contentDescription = null)
                    }
                },
                placeholder = {
                    if (value.isEmpty()) {
                        Text(TextValue("Введите пароль"), color = grayA220)
                    }
                },
                isError = isError,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
        }
        if (isError) {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                text = TextValue("Пароли не совпадают"),
                color = errorLight,
                fontSize = 12.sp
            )
        } else if (value.length < 4 && value.isNotEmpty()) {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                text = TextValue("Введите не менее 4 символов"),
                color = errorLight,
                fontSize = 12.sp
            )
        }
    }
}
