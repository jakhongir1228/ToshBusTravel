package uz.toshshahartransxizmat.toshbustravel.components.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Suppress("ComposableNaming", "unused") // internal usage
@Immutable
sealed class PageHeaderType {

    @Composable
    internal abstract fun content(modifier: Modifier)

    @Immutable
    class Heading(
        val text: String? = null
    ) : PageHeaderType() {

        @Composable
        override fun content(modifier: Modifier) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 14.dp)
                    .then(modifier),
                contentAlignment = Alignment.Center
            ) {
                text?.let { textValue ->
                    Text(
                        text = textValue,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
           // if (javaClass != other?.javaClass) return false

            other as Heading

            return text == other.text
        }

        override fun hashCode(): Int {
            return text.hashCode()
        }

        override fun toString(): String {
            return "Heading(text=$text)"
        }
    }
}
