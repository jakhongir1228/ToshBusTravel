package uz.toshshahartransxizmat.toshbustravel.ui.auth.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue

@Composable
fun TextAuth(
    text:String,
    textClick:String,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = TextValue(text),
                color = Color(0xFF708393),
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.clickable {
                    navigator.pop()
                },
                text = TextValue(textClick),
                color = Color(0xFF007AFF),
                fontSize = 16.sp
            )
        }
    }
}
