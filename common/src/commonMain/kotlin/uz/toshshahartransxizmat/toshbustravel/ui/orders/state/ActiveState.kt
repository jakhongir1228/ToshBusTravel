package uz.toshshahartransxizmat.toshbustravel.ui.orders.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ActiveOrderData

data class ActiveState(
    val activeOrder: ActiveOrderData = ActiveOrderData(
        0,0
    ),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
