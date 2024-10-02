package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class AuthResponseData(
    val data: SignData,
    val message: String?,
    val error: String?,
    val success: Boolean
)
