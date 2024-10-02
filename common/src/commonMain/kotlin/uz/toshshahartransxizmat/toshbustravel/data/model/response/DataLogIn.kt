package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataLogIn(
    val hash: String?,
    val sentOtp: Boolean,
    val userRole: String,
    val access_token: String?
)
