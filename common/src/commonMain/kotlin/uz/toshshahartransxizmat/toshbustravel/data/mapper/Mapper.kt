package uz.toshshahartransxizmat.toshbustravel.data.mapper

import uz.toshshahartransxizmat.toshbustravel.data.model.Vehicles
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataLogIn
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignData

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
