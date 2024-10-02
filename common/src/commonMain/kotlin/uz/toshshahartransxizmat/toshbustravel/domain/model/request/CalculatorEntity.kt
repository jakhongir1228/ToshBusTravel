package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorEntity(
    val busTypeId: Int,
    val isCity: Boolean,
    val distance: Int,
    val travelTime: Int
)
