package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class OrderResponseData(
    val data: OrderData? = null,
    val message: String?,
    val error: String?,
    val success: Boolean
)
