package uz.toshshahartransxizmat.toshbustravel.data.mapper

import uz.toshshahartransxizmat.toshbustravel.data.model.Vehicles
import uz.toshshahartransxizmat.toshbustravel.data.model.response.CalculatorDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ClientDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ContentItem
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataActive
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataAmount
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataClient
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataLogIn
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataOrder
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataPayment
import uz.toshshahartransxizmat.toshbustravel.data.model.response.DataReset
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.OrderDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportDetails
import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ActiveOrderData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AmountData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.CalculatorResponse
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientUpdateData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.DetailsResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.PaymentData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ResetData
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

fun DataReset.toResetData(): ResetData{
    return ResetData(
        otpSent = otpSent,
        completed = completed,
        hash = hash
    )
}

fun DataClient.toClientData(): ClientData {
    return ClientData(
        id = id,
        phoneNumber = phoneNumber,
        fullName = fullName,
        imgPath = imgPath
    )
}

fun ClientDTO.toClientUpdateData(): ClientUpdateData{
    return ClientUpdateData(
        message = message,
        error = error,
        success = success
    )
}

fun OrderDTO.toOrderResData(): OrderResponseData{
    return OrderResponseData(
        data = data?.toOrderData(),
        message = message,
        error = error,
        success = success
    )
}

fun DataOrder.toOrderData(): OrderData{
    return OrderData(
        content = content,
        totalCount = totalCount,
        totalPages = totalPages,
        page = page
    )
}

fun ContentItem.toOrders():Orders {
    return Orders(
        id = id,
        modelName = modelName,
        vehicleNumber = vehicleNumber,
        startDate = startDate,
        endDate = endDate,
        amount = amount,
        status = status
    )
}

fun DataActive.toActiveOrder(): ActiveOrderData{
    return ActiveOrderData(
        id = id,
        price = price
    )
}

fun CalculatorDTO.toCalResponse(): CalculatorResponse{
    return CalculatorResponse(
        data = data.toAmountData(),
        message = message,
        error = error,
        success = success
    )
}

fun DataAmount.toAmountData(): AmountData{
    return AmountData(
        amount = amount
    )
}

fun DataPayment.toPaymentData(): PaymentData{
    return PaymentData(
        hash = hash,
        sentOtp = sentOtp,
        paymentId = paymentId,
        access_token = access_token
    )
}
