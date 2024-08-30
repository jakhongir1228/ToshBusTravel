package uz.toshshahartransxizmat.toshbustravel.ui.auth.state

import kotlinx.coroutines.flow.emptyFlow
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignUpData

data class AuthState(
    val success: SignUpData = SignUpData("",""),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
