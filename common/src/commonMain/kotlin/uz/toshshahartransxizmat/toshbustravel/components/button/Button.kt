package uz.toshshahartransxizmat.toshbustravel.components.button

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uz.toshshahartransxizmat.toshbustravel.components.button.theme.ButtonRippleTheme
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.Icon
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue

@Composable
fun Button(
    model: ButtonModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) = Button(
    text = model.text,
    modifier = modifier,
    size = model.size,
    type = model.type,
    enabled = model.enabled,
    loading = model.loading,
    leftIcon = model.leftIcon,
    rightIcon = model.rightIcon,
    onClick = onClick
)

@Suppress("LongMethod")
@Composable
fun Button(
    text: TextValue,
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.Medium,
    type: ButtonType = ButtonType.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    leftIcon: IconValue? = null,
    rightIcon: IconValue? = null,
    onClick: () -> Unit
) = ButtonRippleTheme {
    Box(
        modifier = modifier
            .background(
                color = type.backgroundColor(enabled = !loading && enabled).value,
                shape = size.shape
            )
            .border(
                width = 1.dp,
                color = type.borderColor(enabled = !loading && enabled).value,
                shape = size.shape
            )
            .clip(size.shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, color = type.rippleBackgroundColor),
                enabled = !loading && enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .height(height = size.height)
                .padding(all = size.padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(16.dp),
                    color = type.progressBarColor,
                    strokeWidth = 1.dp
                )
            } else {
                val color by type.textColor(enabled = !loading && enabled)

                leftIcon?.let {
                    Icon(modifier = Modifier.size(size.iconSize), icon = it, tint = color)
                }
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = text,
                    color = color
                )
                rightIcon?.let {
                    Icon(modifier = Modifier.size(size.iconSize), icon = it, tint = color)
                }
            }
        }
    }
}
