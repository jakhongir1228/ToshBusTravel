package uz.toshshahartransxizmat.toshbustravel.ui.auth.state

import kotlinx.coroutines.flow.emptyFlow
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
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
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
