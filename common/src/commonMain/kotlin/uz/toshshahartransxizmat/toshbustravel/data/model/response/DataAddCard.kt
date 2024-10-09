package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataAddCard(
    val cardToken: String,
    val smsNotificationNumber: String? = null
)
