package uz.toshshahartransxizmat.toshbustravel.share

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

@Composable
actual fun isImeVisible(): Boolean {
    val view = LocalView.current
    val rootView = view.rootView
    val rect = android.graphics.Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val screenHeight = rootView.height
    val keypadHeight = screenHeight - rect.bottom
    return keypadHeight > screenHeight * 0.15
}