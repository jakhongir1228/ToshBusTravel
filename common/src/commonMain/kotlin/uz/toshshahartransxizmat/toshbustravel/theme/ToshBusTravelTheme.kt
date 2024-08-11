package uz.toshshahartransxizmat.toshbustravel.theme

import androidx.compose.runtime.Composable

@Composable
expect fun ToshBusTravelTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
)
