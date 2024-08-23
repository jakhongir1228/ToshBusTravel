package uz.toshshahartransxizmat.toshbustravel.components.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import uz.toshshahartransxizmat.toshbustravel.theme.black5
import uz.toshshahartransxizmat.toshbustravel.theme.blue500
import uz.toshshahartransxizmat.toshbustravel.theme.blue650
import uz.toshshahartransxizmat.toshbustravel.theme.blue750
import uz.toshshahartransxizmat.toshbustravel.theme.gray500
import uz.toshshahartransxizmat.toshbustravel.theme.gray700
import uz.toshshahartransxizmat.toshbustravel.theme.plumA700
import uz.toshshahartransxizmat.toshbustravel.theme.plumA800
import uz.toshshahartransxizmat.toshbustravel.theme.silver100
import uz.toshshahartransxizmat.toshbustravel.theme.silver950
import uz.toshshahartransxizmat.toshbustravel.theme.white100

@Suppress("unused")
@Immutable
sealed class ButtonType {

    internal abstract val rippleBackgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get

    internal open val progressBarColor: Color
        @Composable
        @ReadOnlyComposable
        get() = silver100

    private val disabledBackgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get() = backgroundColor.copy(alpha = 0.5f)

    private val disabledTextColor: Color
        @Composable
        @ReadOnlyComposable
        get() = textColor.copy(alpha = 0.5f)

    internal open val borderColor: Color
        @Composable
        @ReadOnlyComposable
        get() = Color.Transparent

    internal open val disabledBorderColor: Color
        @Composable
        @ReadOnlyComposable
        get() = Color.Transparent

    internal abstract val backgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get

    internal abstract val textColor: Color
        @Composable
        @ReadOnlyComposable
        get

    @Composable
    internal fun backgroundColor(enabled: Boolean): State<Color> {
        val targetValue = when {
            !enabled -> disabledBackgroundColor
            else -> backgroundColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    internal fun borderColor(enabled: Boolean): State<Color> {
        val targetValue = when {
            !enabled -> disabledBorderColor
            else -> borderColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    internal fun textColor(enabled: Boolean): State<Color> {
        val targetValue = when {
            !enabled -> disabledTextColor
            else -> textColor
        }
        return rememberUpdatedState(targetValue)
    }

     object Primary : ButtonType() {

        override val backgroundColor
            @Composable
            @ReadOnlyComposable
            get() = blue650

        override val rippleBackgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = blue750

        override val progressBarColor: Color
            @Composable
            @ReadOnlyComposable
            get() = white100

        override val textColor: Color
            @Composable
            @ReadOnlyComposable
            get() = white100
    }

     object Secondary : ButtonType() {

        override val backgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = plumA800

        override val rippleBackgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = plumA700

        override val textColor: Color
            @Composable
            @ReadOnlyComposable
            get() = blue500
    }

     object White : ButtonType() {

        override val backgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = white100

        override val rippleBackgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = gray500

        override val textColor: Color
            @Composable
            @ReadOnlyComposable
            get() = gray700
    }

     object Ghost : ButtonType() {

        override val backgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = silver950

        override val rippleBackgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = silver950

        override val textColor: Color
            @Composable
            @ReadOnlyComposable
            get() = silver100
    }

     object GhostOutline : ButtonType() {

        override val backgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = silver950

        override val rippleBackgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = silver950

        override val borderColor: Color
            @Composable
            @ReadOnlyComposable
            get() = plumA800

        override val disabledBorderColor: Color
            @Composable
            @ReadOnlyComposable
            get() = plumA800

        override val progressBarColor: Color
            @Composable
            @ReadOnlyComposable
            get() = white100

        override val textColor: Color
            @Composable
            @ReadOnlyComposable
            get() = white100
    }

     object Transparent : ButtonType() {

        override val backgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = black5.copy(alpha = 0.1f)

        override val rippleBackgroundColor: Color
            @Composable
            @ReadOnlyComposable
            get() = black5

        override val textColor: Color
            @Composable
            @ReadOnlyComposable
            get() = silver100
    }
}
