package uz.toshshahartransxizmat.toshbustravel.ui.payment.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.response.PaymentData

data class PaymentState (
    val payment: PaymentData = PaymentData("",false,0,null),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
