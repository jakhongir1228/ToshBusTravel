package uz.toshshahartransxizmat.toshbustravel.components.otp.button

import androidx.compose.runtime.Immutable
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonModel
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue

@Immutable
data class OtpButtonModel(
    override val text: TextValue = TextValue("Отправить еще раз"),
    override val type: ButtonType = ButtonType.Primary,
    override val enabled: Boolean = true
) : ButtonModel() {
    override val size = ButtonSize.Medium
}