package uz.toshshahartransxizmat.toshbustravel.ui.card.component.otp

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.otp.state.OtpConfirmationState

class OtpAddCardViewModel: ViewModel() {

    private val _state = MutableStateFlow(
        OtpConfirmationState(
            phoneNumber = "",
            requiredTimeout = 60,
            requiredValueLength = 6
        )
    )
    val state get() = _state.asStateFlow()

    private var timer: Job? = null

    fun onOtpValueChanged(value: String) {
        _state.value = _state.value.copy(value = value, isInputCompleted = value.length == _state.value.requiredValueLength)
    }

    fun onSmsReceived(smsCode: String) {
        _state.value = _state.value.copy(value = smsCode, isInputCompleted = true)
    }

    fun startTimer() {
        timer?.cancel()
        timer = viewModelScope.launch {
            for (i in _state.value.requiredTimeout downTo 0) {
                delay(1000L)
                _state.value = _state.value.copy(timerText = TextValue("Повтор через 0:$i c"), isTimerCompleted = i == 0)
            }
            _state.value = _state.value.copy(isResendAvailable = true)
        }
    }

    fun resendOtp() {
        if (_state.value.isResendAvailable) {
            startTimer()
        }
    }
}