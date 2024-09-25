package uz.toshshahartransxizmat.toshbustravel.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toAuthData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toCalResponse
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toClientData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toClientUpdateData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toDetailsData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toOrderResData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toOrders
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toResetData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toTransports
import uz.toshshahartransxizmat.toshbustravel.data.network.KtorService
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
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val ktorService: KtorService
): NetworkRepository {
    override suspend fun getVehicles(query: String, page: Int, size: Int): Flow<List<Transports>> = flow {
        val r = ktorService.getVehicles(query,page,size)
        emit(r.content.map { it.toTransports() })
    }

    override suspend fun postSignUp(signUp: SignUpEntity): Flow<AuthResponseData> = flow {
        val r = ktorService.postSignUp(signUp)
        emit(r.toAuthData())
    }

    override suspend fun postSignIn(signInEntity: SignInEntity): Flow<AuthResponseData> = flow {
        val r = ktorService.postSignIn(signInEntity)
        emit(r.toAuthData())
    }

    override suspend fun postResetPassword(resetEntity: ResetEntity): Flow<ResetData> = flow {
        val r = ktorService.postResetPassword(resetEntity)
        emit(r.data.toResetData())
    }

    override suspend fun postPasswordVerify(resetEntity: ResetEntity): Flow<ResetData> = flow {
        val r = ktorService.postPasswordVerify(resetEntity)
        emit(r.data.toResetData())
    }

    override suspend fun getDetails(id: Int): Flow<DetailsResponseData> = flow {
        val r = ktorService.getDetails(id)
        emit(r.toDetailsData())
    }

    override suspend fun getClientInfo(): Flow<ClientData> = flow {
        val r = ktorService.getClientInfo()
        r.data?.toClientData()?.let { emit(it) }
    }

    override suspend fun postUpdateClient(userProfileEntity: UserProfileEntity): Flow<ClientUpdateData> = flow {
        val r = ktorService.postUpdateClient(userProfileEntity)
        emit(r.toClientUpdateData())
    }

    override suspend fun postCreateOrder(createOrderEntity: CreateOrderEntity): Flow<OrderResponseData> = flow {
        val r = ktorService.postCreateOrder(createOrderEntity)
        emit(r.toOrderResData())
    }

    override suspend fun getActiveOrder(): Flow<OrderResponseData> = flow {
        val r = ktorService.getActiveOrder()
        emit(r.toOrderResData())
    }

    override suspend fun postPayOrder(payOrderEntity: PayOrderEntity): Flow<OrderResponseData> = flow {
        val r = ktorService.postPayOrder(payOrderEntity)
        emit(r.toOrderResData())
    }

    override suspend fun postCalculator(calculatorEntity: CalculatorEntity): Flow<CalculatorResponse> = flow {
        val r = ktorService.postCalculator(calculatorEntity)
        emit(r.toCalResponse())
    }

    override suspend fun getOrders(): Flow<List<Orders>> = flow {
        val r = ktorService.getOrders()
        r.data?.content?.map { it.toOrders() }?.let { emit(it) }
    }

}
