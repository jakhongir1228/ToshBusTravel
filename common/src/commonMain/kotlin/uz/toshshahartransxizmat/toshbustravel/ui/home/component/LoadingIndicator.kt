package uz.toshshahartransxizmat.toshbustravel.ui.home.component


import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import uz.toshshahartransxizmat.toshbustravel.theme.blue650

@Composable
internal fun LoadingIndicator(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            )
        )
    )

    Canvas(
        modifier = modifier
            .size(48.dp)
    ) {
        val lineCount = 12
        val lineLength = size.minDimension / 6
        val lineWidth = size.minDimension / 20
        val colors = (0 until lineCount).map {
            blue650.copy(alpha = it / lineCount.toFloat())
        }

        (0 until lineCount).forEach { i ->
            val lineRotation = (i * 360f / lineCount) + rotation

            rotate(lineRotation, center) {
                drawLine(
                    color = colors[i],
                    start = Offset(center.x, center.y - lineLength),
                    end = Offset(center.x, center.y - lineLength * 2),
                    strokeWidth = lineWidth,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

