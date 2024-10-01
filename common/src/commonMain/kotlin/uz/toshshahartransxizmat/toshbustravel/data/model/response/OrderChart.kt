package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class OrderChart(
    val id: Int,
    val orderStart: String,
    val orderEnd: String
)
