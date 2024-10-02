package uz.toshshahartransxizmat.toshbustravel.domain.model

data class Transports(
    val id: Int,
    val modelName: String,
    val number: String,
    val type: String,
    val passengerCapacity: Int,
    val url: String?,
    val urlToImage: String?
)
