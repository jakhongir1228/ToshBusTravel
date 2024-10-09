package uz.toshshahartransxizmat.toshbustravel.components.input.text

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import uz.toshshahartransxizmat.toshbustravel.theme.borderColor200
import uz.toshshahartransxizmat.toshbustravel.theme.grayA220
import uz.toshshahartransxizmat.toshbustravel.theme.red500

@Suppress("unused")
@Immutable
internal class TextInputColors {

    @Composable
    fun borderColor(
        readOnly: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            isError -> red500
            !readOnly && focused -> borderColor200
            else -> borderColor200
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    fun captionColor(isError: Boolean): State<Color> {
        val targetValue = when {
            isError -> red500
            else -> borderColor200
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    fun labelColor(isError: Boolean): State<Color> {
        val targetValue = when {
            isError -> red500
            else -> MaterialTheme.colorScheme.scrim
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    fun placeholderColor(isError: Boolean): State<Color> {
        val targetValue = when {
            isError -> red500
            else -> grayA220
        }
        return rememberUpdatedState(targetValue)
    }
}
