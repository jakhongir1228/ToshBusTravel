package uz.toshshahartransxizmat.toshbustravel.ui.amount.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

@Composable
internal fun OrderTypeSelector(
    title: String,
    modifier: Modifier = Modifier,
    onOptionSelected: (Boolean) -> Unit
) {
    var selectedIsCity by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            RadioButton(
                selected = selectedIsCity,
                onClick = {
                    selectedIsCity = true
                    onOptionSelected(true)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = blueA220
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = getStrings("order_within_city"), fontSize = 16.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            RadioButton(
                selected = !selectedIsCity,
                onClick = {
                    selectedIsCity = false
                    onOptionSelected(false)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = blueA220
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = getStrings("order_outside_city"), fontSize = 16.sp)
        }
    }
}
