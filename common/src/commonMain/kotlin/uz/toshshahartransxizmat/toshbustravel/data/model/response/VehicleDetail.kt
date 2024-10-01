package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class VehicleDetail(
    val id: Int,
    val name: String,
    val iconPath: String
)
