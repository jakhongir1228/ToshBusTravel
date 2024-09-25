package uz.toshshahartransxizmat.toshbustravel.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CalculatorEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CreateOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.PayOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.UserProfileEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.CalculatorResponse
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientUpdateData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.DetailsResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderContentData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ResetData

interface NetworkRepository {
    suspend fun getVehicles(query: String, page: Int, size: Int): Flow<List<Transports>>

    suspend fun postSignUp(signUp: SignUpEntity):Flow<AuthResponseData>

    suspend fun postSignIn(signInEntity: SignInEntity):Flow<AuthResponseData>

    suspend fun postResetPassword(resetEntity: ResetEntity): Flow<ResetData>

    suspend fun postPasswordVerify(resetEntity: ResetEntity): Flow<ResetData>

    suspend fun getDetails(id:Int): Flow<DetailsResponseData>

    suspend fun getClientInfo(): Flow<ClientData>

    suspend fun postUpdateClient(userProfileEntity: UserProfileEntity): Flow<ClientUpdateData>

    suspend fun postCreateOrder(createOrderEntity: CreateOrderEntity): Flow<OrderResponseData>

    suspend fun getActiveOrder(): Flow<OrderResponseData>

    suspend fun postPayOrder(payOrderEntity: PayOrderEntity): Flow<OrderResponseData>

    suspend fun postCalculator(calculatorEntity: CalculatorEntity): Flow<CalculatorResponse>

    suspend fun getOrders(): Flow<List<Orders>>
}