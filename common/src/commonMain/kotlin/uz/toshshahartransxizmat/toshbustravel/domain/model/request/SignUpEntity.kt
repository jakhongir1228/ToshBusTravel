package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpEntity(
    val username:String,
    val password:String,
    val code:String,
    val hash:String,
    val deviceId:String
)
