package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class VehicleQueryParams(
    val query:String,
    val page:Int,
    val size:Int
)
