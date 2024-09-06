package uz.toshshahartransxizmat.toshbustravel.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Vehicles(
    val id: Int,
    val modelName: String,
    val number: String,
    val type: String,
    val passengerCapacity: Int,
    val url: String?,
    val urlToImage: String?
)