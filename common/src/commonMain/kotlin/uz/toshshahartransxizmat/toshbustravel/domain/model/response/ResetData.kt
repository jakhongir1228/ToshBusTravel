package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class ResetData(
    val otpSent: Boolean,
    val completed: Boolean,
    val hash: String?,
)
