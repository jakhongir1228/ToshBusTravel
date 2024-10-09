package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class AddCardData(
    val cardToken: String,
    val smsNotificationNumber: String? = null
)
