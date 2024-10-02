package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LogInDTO(
    val data: DataLogIn,
    val error: String?,
    val message: String,
    val code: Int,
    val timeout: Boolean,
    val success: Boolean
)
