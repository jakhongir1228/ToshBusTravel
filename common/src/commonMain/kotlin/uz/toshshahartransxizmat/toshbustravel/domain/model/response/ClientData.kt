package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class ClientData(
    val id: Int,
    val phoneNumber: String,
    val fullName: String,
    val imgBase64: String?,
    val imgPath: String?,
)
