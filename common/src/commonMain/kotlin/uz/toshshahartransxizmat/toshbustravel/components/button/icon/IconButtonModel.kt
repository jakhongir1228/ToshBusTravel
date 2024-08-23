package uz.toshshahartransxizmat.toshbustravel.components.button.icon

import androidx.compose.runtime.Immutable
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue
import uz.toshshahartransxizmat.toshbustravel.components.model.Model
import uz.toshshahartransxizmat.toshbustravel.components.model.UnspecifiedId

abstract class IconButtonModel : Model {
    override val id: String = Model.UnspecifiedId
    abstract val icon: IconValue
    open val size: ButtonSize = defaultIconButtonSize
    open val type: ButtonType = defaultIconButtonType
    open val enabled: Boolean = true

    companion object {
        val defaultIconButtonSize = ButtonSize.Large
        val defaultIconButtonType = ButtonType.Primary

        operator fun invoke(
            id: String = Model.UnspecifiedId,
            icon: IconValue,
            size: ButtonSize = defaultIconButtonSize,
            type: ButtonType = defaultIconButtonType,
            enabled: Boolean = true,
        ): IconButtonModel {
            return DefaultImpl(
                id = id,
                icon = icon,
                size = size,
                type = type,
                enabled = enabled
            )
        }

        fun IconButtonModel.copy(
            id: String = this.id,
            icon: IconValue,
            size: ButtonSize = this.size,
            type: ButtonType = this.type,
            enabled: Boolean = this.enabled,
        ): IconButtonModel {
            return invoke(
                id = id,
                icon = icon,
                size = size,
                type = type,
                enabled = enabled
            )
        }

        @Immutable
        private data class DefaultImpl(
            override val id: String,
            override val icon: IconValue,
            override val size: ButtonSize,
            override val type: ButtonType,
            override val enabled: Boolean
        ) : IconButtonModel()
    }
}
