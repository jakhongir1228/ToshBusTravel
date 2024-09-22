package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataClient(
    val id: Int,
    val phoneNumber: String,
    val fullName: String,
    val imgPath: String?,
)
