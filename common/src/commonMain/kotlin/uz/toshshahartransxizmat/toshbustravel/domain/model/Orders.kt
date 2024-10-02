package uz.toshshahartransxizmat.toshbustravel.domain.model

data class Orders(
    val id: Int,
    val modelName: String,
    val vehicleNumber: String,
    val startDate: String,
    val endDate: String,
    val amount: Long,
    val status: String
)
