package uz.toshshahartransxizmat.toshbustravel.data.mapper

import uz.toshshahartransxizmat.toshbustravel.data.model.Vehicles
import uz.toshshahartransxizmat.toshbustravel.data.model.response.Data
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataLogIn
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignInData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignUpData

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

fun Data.toSignUpData():SignUpData{
    return SignUpData(
        hash = hash
    )
}

fun DataLogIn.toSignInData():SignInData{
    return SignInData(
        accessToken = access_token
    )
}