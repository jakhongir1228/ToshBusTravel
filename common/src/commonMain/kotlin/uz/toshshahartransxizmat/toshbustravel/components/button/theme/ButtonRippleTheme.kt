package uz.toshshahartransxizmat.toshbustravel.components.button.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Composable
internal fun ButtonRippleTheme(
    content: @Composable () -> Unit
) = CompositionLocalProvider(LocalRippleTheme provides ButtonRippleTheme) {
    content()
}

@Immutable
private object ButtonRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = Color.Transparent

    @Composable
    override fun rippleAlpha() = ButtonRippleAlpha
}

private val ButtonRippleAlpha = RippleAlpha(
    draggedAlpha = 1.0f,
    focusedAlpha = 1.0f,
    hoveredAlpha = 1.0f,
    pressedAlpha = 1.00f
)
