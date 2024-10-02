package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ContentItem(
    val id: Int,
    val modelName: String,
    val vehicleNumber: String,
    val startDate: String,
    val endDate: String,
    val amount: Long,
    val status: String
)
