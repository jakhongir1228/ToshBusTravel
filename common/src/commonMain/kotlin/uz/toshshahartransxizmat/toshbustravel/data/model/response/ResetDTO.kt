package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResetDTO(
    val data: DataReset,
    val error: String?,
    val message: String,
    val code: Int,
    val timeout: Boolean,
    val success: Boolean
)
