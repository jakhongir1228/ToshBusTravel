package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ClientDTO(
    val status: Status,
    val details: Details?,
    val data: DataClient? = null,
    val error: String?,
    val message: String,
    val code: Int,
    val timeout: Boolean,
    val success: Boolean
)
