package uz.toshshahartransxizmat.toshbustravel.ui.payment.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        val formatted = buildString {
            for (i in trimmed.indices) {
                if (i % 4 == 0 && i != 0) append(' ')
                append(trimmed[i])
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset <= 4) offset
                else if (offset <= 8) offset + 1
                else if (offset <= 12) offset + 2
                else if (offset <= 16) offset + 3
                else 19
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (offset <= 5) offset
                else if (offset <= 10) offset - 1
                else if (offset <= 15) offset - 2
                else if (offset <= 19) offset - 3
                else 16
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetTranslator)
    }
}