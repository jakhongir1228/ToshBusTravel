package uz.toshshahartransxizmat.toshbustravel.ui.auth.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

internal class PhoneNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = buildString {
            for (i in text.indices) {
                append(text[i])
                if (i == 1 || i == 4 || i == 6) {
                    append(' ')
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset + 1
                    offset <= 7 -> offset + 2
                    else -> offset + 3
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 6 -> offset - 1
                    offset <= 9 -> offset - 2
                    else -> offset - 3
                }
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}
