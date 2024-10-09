package uz.toshshahartransxizmat.toshbustravel.domain.model

data class Cards(
    val id: Int,
    val token: String,
    val cardNumber: String,
    val expiryDate: String,
    val holderFullName: String,
    val bankName: String,
    val type: String?= null,
    val smsNotificationNumber: String? = null
)
