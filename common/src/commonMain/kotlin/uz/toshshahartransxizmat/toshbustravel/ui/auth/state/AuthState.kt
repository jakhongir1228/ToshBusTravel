package uz.toshshahartransxizmat.toshbustravel.ui.auth.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ResetData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignData

data class AuthState(
    val success: AuthResponseData = AuthResponseData(
        data = SignData(
            hash = null,
            sentOtp = false,
            accessToken = null
        ),
        message =  null,
        error = null,
        success = false
    ),
    val successReset: ResetData = ResetData(otpSent = false,completed = false,null),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
