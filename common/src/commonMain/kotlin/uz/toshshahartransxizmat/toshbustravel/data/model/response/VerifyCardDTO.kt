package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class VerifyCardDTO(
    val data: DataVerifyCard,
    val error: String? = null,
    val message: String,
    val code: Int,
    val success: Boolean,
    val timeout: Boolean
)
