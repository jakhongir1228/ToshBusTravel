package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TransportDTO(
    val status: Status,
    val details: Details?,
    val data: TransportDetails,
    val error: String?,
    val message: String,
    val code: Int,
    val success: Boolean,
    val timeout: Boolean
)
