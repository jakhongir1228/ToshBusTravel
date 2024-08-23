package uz.toshshahartransxizmat.toshbustravel.components.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
sealed class ButtonSize {
    internal abstract val height: Dp
    internal abstract val padding: Dp

    internal abstract val shape: Shape
        @Composable
        @ReadOnlyComposable
        get

    internal abstract val typography: TextStyle
        @Composable
        @ReadOnlyComposable
        get

    internal open val iconSize = 24.dp

    @Immutable
     object Large : ButtonSize() {
        override val height = 48.dp
        override val padding = 12.dp

        override val shape: Shape
            @Composable
            @ReadOnlyComposable get() = RoundedCornerShape(12.dp)


        override val typography: TextStyle
            @Composable
            @ReadOnlyComposable
            get() = MaterialTheme.typography.bodyLarge


    }

    @Immutable
     object Medium : ButtonSize() {
        override val height = 40.dp
        override val padding = 8.dp

        override val shape: Shape
            @Composable
            @ReadOnlyComposable
            get() = RoundedCornerShape(12.dp)

        override val typography: TextStyle
            @Composable
            @ReadOnlyComposable
            get() = MaterialTheme.typography.bodyMedium
    }

    @Immutable
     object Small : ButtonSize() {
        override val height = 32.dp
        override val padding = 6.dp

        override val shape: Shape
            @Composable
            @ReadOnlyComposable
            get() = RoundedCornerShape(8.dp)

        override val typography: TextStyle
            @Composable
            @ReadOnlyComposable
            get() = MaterialTheme.typography.bodySmall

        override val iconSize = 20.dp
    }
}
