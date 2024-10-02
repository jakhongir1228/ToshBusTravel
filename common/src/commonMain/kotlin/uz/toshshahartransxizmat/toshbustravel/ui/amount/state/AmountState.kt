package uz.toshshahartransxizmat.toshbustravel.ui.amount.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AmountData

data class AmountState(
    val success: AmountData = AmountData(
        amount = 0.0
    ),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
