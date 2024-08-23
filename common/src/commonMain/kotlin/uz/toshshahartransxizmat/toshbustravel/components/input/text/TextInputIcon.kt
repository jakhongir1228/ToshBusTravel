package uz.toshshahartransxizmat.toshbustravel.components.input.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.Icon
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInputDefaultSize.MinHeight

@Suppress("ComposableNaming", "unused") // internal usage
@Immutable
sealed class TextInputIcon {

    @Composable
    internal abstract fun content(
        enabled: Boolean,
        readOnly: Boolean,
        onValueChange: (String) -> Unit
    )

    @Composable
    internal fun Icon(
        value: IconValue,
        enabled: Boolean,
        readOnly: Boolean,
        onClick: () -> Unit
    ) {
        val clickableModifier = if (!readOnly) {
            Modifier.clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false)
            )
        } else {
            Modifier
        }

        Box(
            modifier = Modifier
                .defaultMinSize(minHeight = MinHeight)
                .padding(start = 8.dp)
                .then(clickableModifier),
            contentAlignment = Alignment.Center,
            content = { Icon(modifier = Modifier.size(24.dp), icon = value) }
        )
    }

     object Clear : TextInputIcon() {

        @Composable
        override fun content(
            enabled: Boolean,
            readOnly: Boolean,
            onValueChange: (String) -> Unit
        ) = Icon(
            value = IconValue("drawable/clear.xml"),
            enabled = enabled,
            readOnly = readOnly,
            onClick = { onValueChange("") }
        )
    }

    private class Custom(
        private val value: IconValue,
        private val onClick: () -> Unit
    ) : TextInputIcon() {

        @Composable
        override fun content(
            enabled: Boolean,
            readOnly: Boolean,
            onValueChange: (String) -> Unit
        ) = Icon(
            value = this.value,
            enabled = enabled,
            readOnly = readOnly,
            onClick = onClick
        )

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
           // if (javaClass != other?.javaClass) return false

            other as Custom

            if (value != other.value) return false
            if (onClick != other.onClick) return false

            return true
        }

        override fun hashCode(): Int {
            var result = value.hashCode()
            result = 31 * result + onClick.hashCode()
            return result
        }
    }

    companion object {
        operator fun invoke(
            value: IconValue,
            onClick: () -> Unit
        ): TextInputIcon {
            return Custom(value, onClick)
        }
    }
}
