package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataLogIn(
    @SerialName("access_token")
    val access_token: String
)
