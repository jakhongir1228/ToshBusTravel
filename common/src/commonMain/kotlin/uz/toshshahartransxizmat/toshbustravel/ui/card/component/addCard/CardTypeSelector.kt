package uz.toshshahartransxizmat.toshbustravel.ui.card.component.addCard

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun CardTypeSelector(
    modifier: Modifier = Modifier,
    onOptionSelected: (Boolean) -> Unit
){
    var selectedIsCard by remember { mutableStateOf(true) }

    Row(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            RadioButton(
                selected = selectedIsCard,
                onClick = {
                    selectedIsCard = true
                    onOptionSelected(true)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = blueA220
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                modifier = Modifier.size(22.dp),
                painter = painterResource("drawable/uzCard.xml"),
                contentDescription = null
            )

            Text(text = getStrings(" | "), fontSize = 18.sp)

            Image(
                modifier = Modifier.size(22.dp),
                painter = painterResource("drawable/humoCard.xml"),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            RadioButton(
                selected = !selectedIsCard,
                onClick = {
                    selectedIsCard = false
                    onOptionSelected(false)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = blueA220
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource("drawable/visaCard.xml"),
                contentDescription = null
            )

            Text(text = getStrings(" | "), fontSize = 20.sp)

            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource("drawable/masterCard.xml"),
                contentDescription = null
            )
        }
    }
}