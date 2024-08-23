package uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.jvm.JvmInline

@Immutable
@JvmInline
value class IconValue private constructor(private val value: Any) {

    @OptIn(ExperimentalResourceApi::class)
    val painter: Painter
        @Composable
        get() = when (value) {
            is ImageVector -> rememberVectorPainter(image = value)
            is String -> painterResource(value)
            else -> unresolvedTypeError(value)
        }

    constructor(
        imageVector: ImageVector
    ) : this(value = imageVector)

    constructor(
        url: String
    ) : this(value = url)

    private fun unresolvedTypeError(type: Any): Nothing {
        error("Unresolved icon type: $type")
    }
}
