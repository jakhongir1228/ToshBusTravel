package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataCard(
    val id: Int,
    val token: String,
    val cardNumber: String,
    val expiryDate: String,
    val holderFullName: String,
    val bankName: String,
    val type: String?= null,
    val smsNotificationNumber: String? = null
)