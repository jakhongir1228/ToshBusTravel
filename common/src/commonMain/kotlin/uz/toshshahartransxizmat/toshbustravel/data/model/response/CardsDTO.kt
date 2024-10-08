package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CardsDTO(
    val data: List<DataCard> = emptyList(),
    val error: String? = null,
    val message: String,
    val code: Int,
    val success: Boolean,
    val timeout: Boolean
)
