package uz.toshshahartransxizmat.toshbustravel.ui.orders.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders

data class OrderState(
    val orders: List<Orders> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)