package uz.toshshahartransxizmat.toshbustravel.components.input.text

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.VisualTransformation
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.model.Model
import uz.toshshahartransxizmat.toshbustravel.components.model.UnspecifiedId

abstract class TextInputModel : Model {
    abstract val value: String
    open val enabled: Boolean = true
    open val readOnly: Boolean = false
    open val label: TextValue? = null
    open val placeholder: TextValue? = null
    open val caption: TextValue? = null
    open val isError: Boolean = false
    open val loading: Boolean = false
    open val visualTransformation: VisualTransformation = VisualTransformation.None
    open val keyboardOptions: KeyboardOptions = KeyboardOptions.Default
    open val keyboardActions: KeyboardActions = KeyboardActions.Default
    open val singleLine: Boolean = true

    companion object {

        operator fun invoke(
            id: String = Model.UnspecifiedId,
            value: String,
            enabled: Boolean = true,
            readOnly: Boolean = false,
            label: TextValue? = null,
            placeholder: TextValue? = null,
            caption: TextValue? = null,
            isError: Boolean = false,
            loading: Boolean = false,
            visualTransformation: VisualTransformation = VisualTransformation.None,
            keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
            keyboardActions: KeyboardActions = KeyboardActions.Default,
            singleLine: Boolean = true,
        ): TextInputModel {
            return DefaultImpl(
                id = id,
                value = value,
                enabled = enabled,
                readOnly = readOnly,
                label = label,
                placeholder = placeholder,
                caption = caption,
                isError = isError,
                loading = loading,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine
            )
        }

        fun TextInputModel.copy(
            id: String = this.id,
            value: String = this.value,
            enabled: Boolean = this.enabled,
            readOnly: Boolean = this.readOnly,
            label: TextValue? = this.label,
            placeholder: TextValue? = this.placeholder,
            caption: TextValue? = this.caption,
            isError: Boolean = this.isError,
            loading: Boolean = this.loading,
            visualTransformation: VisualTransformation = this.visualTransformation,
            keyboardOptions: KeyboardOptions = this.keyboardOptions,
            keyboardActions: KeyboardActions = this.keyboardActions,
            singleLine: Boolean = this.singleLine,
        ): TextInputModel {
            return invoke(
                id = id,
                value = value,
                enabled = enabled,
                readOnly = readOnly,
                label = label,
                placeholder = placeholder,
                caption = caption,
                isError = isError,
                loading = loading,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine
            )
        }

        @Immutable
        private data class DefaultImpl(
            override val id: String,
            override val value: String,
            override val enabled: Boolean,
            override val readOnly: Boolean,
            override val label: TextValue?,
            override val placeholder: TextValue?,
            override val caption: TextValue?,
            override val isError: Boolean,
            override val loading: Boolean,
            override val visualTransformation: VisualTransformation,
            override val keyboardOptions: KeyboardOptions,
            override val keyboardActions: KeyboardActions,
            override val singleLine: Boolean = true,
        ) : TextInputModel()
    }
}
