package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInEntity(
    val username:String,
    val password:String,
    val platform:String,
    val code:String,
    val hash:String,
    val deviceId:String,
    val version:String,
    val lang:String,
    val deviceName:String,
    val ipAddress:String
)
