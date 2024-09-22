package uz.toshshahartransxizmat.toshbustravel.domain.model.response

import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders

data class OrderContentData(
    val orders: List<Orders> = emptyList(),
    val message: String?,
    val error: String?,
    val success: Boolean
)
