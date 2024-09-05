package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val message: String,
    val details: Details,
    val code: Int,
    val success: Boolean,
    val error: String,
    val timeout: Boolean
)
