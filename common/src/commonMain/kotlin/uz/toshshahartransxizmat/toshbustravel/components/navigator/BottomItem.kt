package uz.toshshahartransxizmat.toshbustravel.components.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun RowScope.BottomItem(tab: Tab) {
    val navigator = LocalTabNavigator.current
    val defaultIcon = rememberVectorPainter(Icons.Default.Star)

    NavigationBarItem (
        modifier = Modifier
            .background(Color.White),
        selected = navigator.current == tab,
        onClick = { navigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon ?: defaultIcon,
                contentDescription = "icon"
            )
        },
        label = {
            Text(
                text = tab.options.title
            )
        }
    )
}
