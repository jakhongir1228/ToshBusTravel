package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class SignData(
    val hash: String?,
    val sentOtp: Boolean,
    val userRole:String,
    val accessToken:String?,
)
