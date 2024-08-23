package uz.toshshahartransxizmat.toshbustravel.components.button

import androidx.compose.runtime.Immutable
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.model.Model
import uz.toshshahartransxizmat.toshbustravel.components.model.UnspecifiedId

abstract class ButtonModel : Model {
    override val id: String = Model.UnspecifiedId
    abstract val text: TextValue
    open val size: ButtonSize = defaultButtonSize
    open val type: ButtonType = defaultButtonType
    open val enabled: Boolean = true
    open val loading: Boolean = false
    open val leftIcon: IconValue? = null
    open val rightIcon: IconValue? = null

    companion object {
        val defaultButtonSize = ButtonSize.Medium
        val defaultButtonType = ButtonType.Primary

        operator fun invoke(
            id: String = Model.UnspecifiedId,
            text: TextValue,
            size: ButtonSize = defaultButtonSize,
            type: ButtonType = defaultButtonType,
            enabled: Boolean = true,
            loading: Boolean = false,
            leftIcon: IconValue? = null,
            rightIcon: IconValue? = null
        ): ButtonModel {
            return DefaultImpl(
                id = id,
                text = text,
                size = size,
                type = type,
                enabled = enabled,
                loading = loading,
                leftIcon = leftIcon,
                rightIcon = rightIcon
            )
        }

        fun ButtonModel.copy(
            id: String = this.id,
            text: TextValue = this.text,
            size: ButtonSize = this.size,
            type: ButtonType = this.type,
            enabled: Boolean = this.enabled,
            loading: Boolean = this.loading,
            leftIcon: IconValue? = this.leftIcon,
            rightIcon: IconValue? = this.rightIcon
        ): ButtonModel {
            return invoke(
                id = id,
                text = text,
                size = size,
                type = type,
                enabled = enabled,
                loading = loading,
                leftIcon = leftIcon,
                rightIcon = rightIcon
            )
        }

        @Immutable
        private data class DefaultImpl(
            override val id: String,
            override val text: TextValue,
            override val size: ButtonSize,
            override val type: ButtonType,
            override val enabled: Boolean,
            override val loading: Boolean,
            override val leftIcon: IconValue?,
            override val rightIcon: IconValue?
        ) : ButtonModel()
    }
}
