package uz.toshshahartransxizmat.toshbustravel.data.mapper

import uz.toshshahartransxizmat.toshbustravel.data.model.Vehicles
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataLogIn
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportDetails
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.DetailsResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.TransportDetailsData

fun Vehicles.toTransports(): Transports {
    return Transports(
        id = id,
        modelName = modelName,
        number = number,
        type = type,
        passengerCapacity = passengerCapacity,
        url = url,
        urlToImage = urlToImage
    )
}

fun LogInDTO.toAuthData(): AuthResponseData {
    return AuthResponseData(
        data = data.toSignData(),
        message = message,
        error = error.toString(),
        success = success
    )
}

fun DataLogIn.toSignData():SignData{
    return SignData(
        hash = hash,
        sentOtp = sentOtp,
        accessToken = access_token
    )
}

fun TransportDTO.toDetailsData(): DetailsResponseData {
    return DetailsResponseData(
        data = data.toTransportDetailsData(),
        message = message,
        error = error,
        success = success
    )
}

fun TransportDetails.toTransportDetailsData(): TransportDetailsData{
    return TransportDetailsData(
        vehicleId = vehicleId,
        busTypeId = busTypeId,
        modelName = modelName,
        passengerCapacity = passengerCapacity,
        vehicleDetails = vehicleDetails,
        orderCharts = orderCharts
    )
}
