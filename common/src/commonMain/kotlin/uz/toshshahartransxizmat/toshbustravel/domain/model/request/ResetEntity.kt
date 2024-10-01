package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ResetEntity(
    val username:String,
    val newPassword:String,
    val code:String,
    val hash:String,
    val deviceId:String,
)
