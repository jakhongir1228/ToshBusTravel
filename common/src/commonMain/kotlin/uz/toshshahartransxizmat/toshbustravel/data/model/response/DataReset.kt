package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataReset(
    val otpSent: Boolean,
    val completed: Boolean,
    val hash: String? = null
)
