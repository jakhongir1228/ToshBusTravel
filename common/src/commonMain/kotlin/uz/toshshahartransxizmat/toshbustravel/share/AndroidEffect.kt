package uz.toshshahartransxizmat.toshbustravel.share

import androidx.compose.runtime.Composable

@Composable
expect fun AndroidEffect(onSmsReceived: (String) -> Unit)