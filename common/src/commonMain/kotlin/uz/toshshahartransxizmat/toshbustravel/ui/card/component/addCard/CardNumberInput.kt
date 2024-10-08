package uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
internal fun CardNumberInput(
    title: String,
    modifier: Modifier = Modifier,
    onCardNumberChange: (String) -> Unit
) {
    var cardNumber by remember { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current

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
                value = cardNumber,
                onValueChange = { newValue ->
                    if (newValue.length <= 16 && newValue.all { it.isDigit() }) {
                        cardNumber = newValue
                        onCardNumberChange(cardNumber)
                    }
                },
                placeholder = {
                    Text(
                        text = "---- ---- ---- ----",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                visualTransformation = CardNumberVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 18.sp),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )

            IconButton(onClick = {
              //  clipboardManager.setText(cardNumber)
            }) {
                Image(
                    painter = painterResource("drawable/iconScan.xml"),
                    contentDescription = "Copy card number"
                )
            }
        }

    }
}
