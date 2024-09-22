package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class PayOrderEntity(
    val orderId: Int,
    val cardNumber: String,
    val cardExpiryDate: String,
    val transactionId: Int
)
