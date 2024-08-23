package uz.toshshahartransxizmat.toshbustravel.components.otp.state

import androidx.compose.runtime.Immutable
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.otp.button.OtpButtonModel

@Immutable
internal data class OtpConfirmationState (
    val buttonModel: OtpButtonModel?,
    val errorText: TextValue?,
    val isInputCompleted: Boolean,
    val isInputEnabled: Boolean,
    val isResendAvailable: Boolean,
    val isTimerCompleted: Boolean,
    val phoneNumber: String,
    val requiredTimeout: Int,
    val requiredValueLength: Int,
    val timerText: TextValue?,
    val value: String
) {

    constructor(
        phoneNumber: String,
        requiredTimeout: Int,
        requiredValueLength: Int
    ) : this(
        buttonModel = OtpButtonModel(),
        errorText = null,
        isInputCompleted = false,
        isInputEnabled = false,
        isResendAvailable = false,
        isTimerCompleted = false,
        phoneNumber = phoneNumber,
        requiredTimeout = requiredTimeout,
        requiredValueLength = requiredValueLength,
        timerText = null,
        value = ""
    )
}
