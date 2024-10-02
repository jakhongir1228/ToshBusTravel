package uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.response.DetailsResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.TransportDetailsData

data class TransportDetailsState(
    val success: DetailsResponseData = DetailsResponseData(
        data = TransportDetailsData(0,0,"", 0),
        message = null,
        error =  null,
        success = false
    ),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)