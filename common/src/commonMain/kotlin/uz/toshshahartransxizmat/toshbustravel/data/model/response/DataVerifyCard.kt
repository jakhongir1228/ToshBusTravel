package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataVerifyCard(
    val token: String,
    val cardNumber: String,
    val balance: Int,
    val expiryDate: String,
    val externalToken: String?,
    val smsNotificationNumber: String?,
    val type: String?,
    val holderFullName: String,
    val externalStatus: String?,
    val bankName: String,
    val cvv: String?
)
