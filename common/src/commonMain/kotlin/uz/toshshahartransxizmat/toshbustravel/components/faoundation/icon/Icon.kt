package uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import uz.toshshahartransxizmat.toshbustravel.theme.gray700

@Composable
fun Icon(
    icon: IconValue,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    tint: Color? = MaterialTheme.colorScheme.surface
) {
    val painter = icon.painter

    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        colorFilter = tint?.let { ColorFilter.tint(it) }
    )
}
