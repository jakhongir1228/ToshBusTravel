package uz.toshshahartransxizmat.toshbustravel.ui.profile.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ProfileInputField(
    label: String,
    placeholder: String = "",
    initialValue: String = ""
) {
    var text by remember { mutableStateOf(TextFieldValue(initialValue)) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(4.dp))

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(text = placeholder, color = Color.Gray)
                }
                innerTextField()
            }
        )
    }
}