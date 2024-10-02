package uz.toshshahartransxizmat.toshbustravel.components.faoundation.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import uz.toshshahartransxizmat.toshbustravel.components.model.Model
import uz.toshshahartransxizmat.toshbustravel.components.model.UnspecifiedId
import uz.toshshahartransxizmat.toshbustravel.theme.onPrimaryLight
import uz.toshshahartransxizmat.toshbustravel.theme.white100

abstract class TextModel : Model {
    override val id: String = Model.UnspecifiedId
    abstract val text: TextValue
    open val typography: @Composable () -> TextStyle = defaultTypography
    open val color: Color = defaultTextColor
    open val fontSize: TextUnit = TextUnit.Unspecified
    open val textDecoration: TextDecoration? = null
    open val textAlign: TextAlign? = null
    open val overflow: TextOverflow = TextOverflow.Clip
    open val softWrap: Boolean = true
    open val maxLines: Int = Int.MAX_VALUE
    open val minLines: Int = 1

    companion object {
        val defaultTextColor: Color = onPrimaryLight
        val defaultTypography: @Composable () -> TextStyle = { MaterialTheme.typography.bodyMedium }

        operator fun invoke(
            id: String,
            text: TextValue,
            typography: @Composable () -> TextStyle = defaultTypography,
            color: Color = defaultTextColor,
            fontSize: TextUnit = TextUnit.Unspecified,
            textDecoration: TextDecoration? = null,
            textAlign: TextAlign? = null,
            overflow: TextOverflow = TextOverflow.Clip,
            softWrap: Boolean = true,
            maxLines: Int = Int.MAX_VALUE,
            minLines: Int = 1
        ): TextModel {
            return DefaultImpl(
                id = id,
                text = text,
                typography = typography,
                color = color,
                fontSize = fontSize,
                textDecoration = textDecoration,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }

        fun TextModel.copy(
            id: String = this.id,
            text: TextValue = this.text,
            typography: @Composable () -> TextStyle = this.typography,
            color:  Color = this.color,
            fontSize: TextUnit = this.fontSize,
            textDecoration: TextDecoration? = this.textDecoration,
            textAlign: TextAlign? = this.textAlign,
            overflow: TextOverflow = this.overflow,
            softWrap: Boolean = this.softWrap,
            maxLines: Int = this.maxLines,
            minLines: Int = this.minLines
        ): TextModel {
            return invoke(
                id = id,
                text = text,
                typography = typography,
                color = color,
                fontSize = fontSize,
                textDecoration = textDecoration,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }

        @Immutable
        private data class DefaultImpl(
            override val id: String,
            override val text: TextValue,
            override val typography: @Composable () -> TextStyle,
            override val color:  Color,
            override val fontSize: TextUnit,
            override val textDecoration: TextDecoration?,
            override val textAlign: TextAlign?,
            override val overflow: TextOverflow,
            override val softWrap: Boolean,
            override val maxLines: Int,
            override val minLines: Int
        ) : TextModel()
    }
}
