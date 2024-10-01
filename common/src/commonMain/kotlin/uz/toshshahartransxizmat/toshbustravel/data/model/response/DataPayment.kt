package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataPayment(
    val hash: String,
    val sentOtp: Boolean,
    val paymentId: Long,
    val access_token: String?
)
