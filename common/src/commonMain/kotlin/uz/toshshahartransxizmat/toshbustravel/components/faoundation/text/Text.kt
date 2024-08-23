package uz.toshshahartransxizmat.toshbustravel.components.faoundation.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun <T : TextModel> Text(
    model: T,
    modifier: Modifier = Modifier,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) = Text(
    text = model.text,
    modifier = modifier,
    color = model.color,
    textDecoration = model.textDecoration,
    textAlign = model.textAlign,
    overflow = model.overflow,
    softWrap = model.softWrap,
    maxLines = model.maxLines,
    minLines = model.minLines,
    onTextLayout = onTextLayout
)

@Composable
fun Text(
    text: TextValue,
    modifier: Modifier = Modifier,
    color: Color = TextStyle.Default.color,
    fontSize: TextUnit = TextStyle.Default.fontSize,
    textDecoration: TextDecoration? = TextStyle.Default.textDecoration,
    textAlign: TextAlign? = TextStyle.Default.textAlign,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null
) = when (val value = text.text) {
    is AnnotatedString -> {
        BasicText(
            text = value,
            modifier = modifier,
            style = TextStyle.Default.merge(
                color = color,
                fontSize = fontSize,
                textAlign = textAlign ?: TextAlign.Justify,
                textDecoration = textDecoration
            ),
            onTextLayout = onTextLayout,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines
        )
    }
    else -> {
        BasicText(
            text = value.toString(),
            modifier = modifier,
            style = TextStyle.Default.merge(
                color = color,
                fontSize = fontSize,
                textAlign = textAlign ?: TextAlign.Justify,
                textDecoration = textDecoration,
            ),
            onTextLayout = onTextLayout,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines
        )
    }
}
