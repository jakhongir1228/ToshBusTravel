package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyCardEntity(
    val cardToken: String,
    val code: String
)
