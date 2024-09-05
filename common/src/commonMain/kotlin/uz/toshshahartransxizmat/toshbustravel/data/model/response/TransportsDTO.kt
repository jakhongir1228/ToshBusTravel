package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TransportsDTO(
    val status:Status,
    val details:Details?,
    val data:Data,
    val error:String?,
    val message:String,
    val code:Int,
    val timeout:Boolean,
    val success:Boolean
)
