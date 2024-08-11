package uz.toshshahartransxizmat.toshbustravel.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
actual fun ToshBusTravelTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isDarkTheme) darkScheme else lightScheme,
        content = content
    )
}
