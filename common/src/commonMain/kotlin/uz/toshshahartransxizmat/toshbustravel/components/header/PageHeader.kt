package uz.toshshahartransxizmat.toshbustravel.components.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType
import uz.toshshahartransxizmat.toshbustravel.components.button.icon.IconButton

@Suppress("unused")
@Composable
fun PageHeader(
    type: PageHeaderType,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit
) = PageHeader(
    modifier = modifier,
    type = type,
    navigationIconButton = PageHeaderIconButton.Navigation(onNavigationClick)
)

@Composable
fun PageHeader(
    type: PageHeaderType,
    modifier: Modifier = Modifier,
    navigationIconButton: PageHeaderIconButton? = null,
    actionIconButton: PageHeaderIconButton? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        navigationIconButton?.let { button ->
            IconButton(
                icon = button.icon,
                size = ButtonSize.Large,
                type = ButtonType.White,
                onClick = button.onClick
            )
        }
        type.content(modifier = Modifier.weight(weight = 1f))
        actionIconButton?.let { button ->
            IconButton(
                icon = button.icon,
                size = ButtonSize.Large,
                type = ButtonType.White,
                onClick = button.onClick
            )
        }
    }
}
