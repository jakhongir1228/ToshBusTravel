package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class PaymentData(
    val hash: String,
    val sentOtp: Boolean,
    val paymentId: Long,
    val access_token: String?
)
