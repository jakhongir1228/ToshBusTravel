package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataSignUp(
    val hash: String?,
    val sentOtp: Boolean,
    val access_token: String?
)
