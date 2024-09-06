package uz.toshshahartransxizmat.toshbustravel.components.otp.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.toshshahartransxizmat.toshbustravel.components.otp.state.OtpConfirmationState

class OtpViewModel: ViewModel() {
    private val _state = MutableStateFlow(
        OtpConfirmationState(
            phoneNumber = "",
            requiredTimeout = 0,
            requiredValueLength = 4
        )
    )
    val state get() = _state.asStateFlow()


    fun onOtpValueChanged(value: String) {
        _state.value = _state.value.copy(value = value, isInputCompleted = value.length == _state.value.requiredValueLength)
    }

}