package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class CalculatorResponse(
    val data: AmountData,
    val message: String?,
    val error: String?,
    val success: Boolean
)
