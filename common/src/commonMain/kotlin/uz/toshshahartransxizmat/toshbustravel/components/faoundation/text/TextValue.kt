package uz.toshshahartransxizmat.toshbustravel.components.faoundation.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.AnnotatedString
import kotlin.jvm.JvmInline

@Immutable
@JvmInline
value class TextValue private constructor(private val value: Any) {

    internal val text: CharSequence
        @Composable
        @ReadOnlyComposable
        get() = when (value) {
            is AnnotatedString -> value
            is String -> value
            else -> unresolvedTypeError(value)
        }

    constructor(
        text: AnnotatedString
    ) : this(value = text)

    constructor(
        text: String
    ) : this(value = text)

    private fun unresolvedTypeError(type: Any): Nothing {
        error("Unresolved text type: $type")
    }

    private data class ResArgsValue(val resId: Int, val formatArgs: Array<out Any>) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
           // if (javaClass != other?.javaClass) return false

            other as ResArgsValue

            if (resId != other.resId) return false
            if (!formatArgs.contentEquals(other.formatArgs)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = resId
            result = 31 * result + formatArgs.contentHashCode()
            return result
        }
    }
}
