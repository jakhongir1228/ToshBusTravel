package uz.toshshahartransxizmat.toshbustravel.ui.payment.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CardExpiryInput(
    title: String,
    modifier: Modifier = Modifier,
    onCardExpiryChange: (String) -> Unit
) {
    var expiryDate by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ){
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = expiryDate,
                onValueChange = { newValue ->
                    if (newValue.length <= 4 && newValue.all { it.isDigit() }) {
                        expiryDate = newValue
                        onCardExpiryChange(expiryDate)
                    }
                },
                placeholder = {
                    Text(
                        text = "MM/YY",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                visualTransformation = ExpiryDateVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 18.sp),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                maxLines = 1
            )
        }
    }
}
