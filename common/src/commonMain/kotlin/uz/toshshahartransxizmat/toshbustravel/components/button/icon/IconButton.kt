package uz.toshshahartransxizmat.toshbustravel.components.button.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType
import uz.toshshahartransxizmat.toshbustravel.components.button.theme.ButtonRippleTheme
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.Icon
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue

@Suppress("unused")
@Composable
fun IconButton(
    model: IconButtonModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) = IconButton(
    icon = model.icon,
    modifier = modifier,
    size = model.size,
    type = model.type,
    enabled = model.enabled,
    onClick = onClick
)

@Composable
fun IconButton(
    icon: IconValue,
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.Large,
    type: ButtonType = ButtonType.Primary,
    enabled: Boolean = true,
    onClick: () -> Unit
) = ButtonRippleTheme {
    Box(
        modifier = modifier
            .background(color = type.backgroundColor(enabled).value, shape = size.shape)
            .clip(size.shape)
            .border(width = 1.dp, color = type.borderColor(enabled).value, shape = size.shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, color = type.rippleBackgroundColor),
                enabled = enabled,
                onClick = onClick
            ),
    ) {
        Row(
            modifier = Modifier
                .height(height = size.height)
                .padding(all = size.padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier.size(size = size.iconSize),
                icon = icon,
                tint = type.textColor(enabled).value
            )
        }
    }
}
