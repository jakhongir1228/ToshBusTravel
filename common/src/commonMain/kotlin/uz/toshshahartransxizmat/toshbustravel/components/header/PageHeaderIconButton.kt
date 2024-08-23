package uz.toshshahartransxizmat.toshbustravel.components.header

import androidx.compose.runtime.Immutable
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue

@Suppress("ComposableNaming", "unused") // internal usage
@Immutable
sealed class PageHeaderIconButton(
    internal val icon: IconValue,
    internal val onClick: () -> Unit
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
       // if (javaClass != other?.javaClass) return false

        other as PageHeaderIconButton

        if (icon != other.icon) return false
        if (onClick != other.onClick) return false

        return true
    }

    override fun hashCode(): Int {
        var result = icon.hashCode()
        result = 31 * result + onClick.hashCode()
        return result
    }

    override fun toString(): String {
        return "PageHeaderIconButton(icon=$icon, onClick=$onClick)"
    }

    @Immutable
    class Navigation(
        onClick: () -> Unit
    ) : PageHeaderIconButton(
        icon = IconValue("drawable/arrow_back.xml"),
        onClick = onClick
    )

    @Immutable
    private class Custom(
        icon: IconValue,
        onClick: () -> Unit
    ) : PageHeaderIconButton(
        icon = icon,
        onClick = onClick
    )

    companion object {
        operator fun invoke(icon: IconValue, onClick: () -> Unit): PageHeaderIconButton {
            return Custom(icon, onClick)
        }
    }
}
