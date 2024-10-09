package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddCardEntity(
    val cardNumber: String,
    val expiryDate: String
)
