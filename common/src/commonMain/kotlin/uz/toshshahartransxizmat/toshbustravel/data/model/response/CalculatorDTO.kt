package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorDTO(
    val data: DataAmount,
    val error: String?,
    val message: String,
    val code: Int,
    val timeout: Boolean,
    val success: Boolean
)
