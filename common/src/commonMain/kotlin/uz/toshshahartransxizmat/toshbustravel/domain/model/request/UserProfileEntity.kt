package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileEntity(
    val fullName: String,
    val imgBase64: String
)
