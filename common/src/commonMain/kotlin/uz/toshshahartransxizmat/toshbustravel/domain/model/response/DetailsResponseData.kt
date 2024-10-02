package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class DetailsResponseData(
    val data: TransportDetailsData,
    val message: String?,
    val error: String?,
    val success: Boolean
)
