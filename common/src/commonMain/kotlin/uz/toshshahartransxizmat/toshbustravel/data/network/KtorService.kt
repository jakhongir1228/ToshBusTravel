package uz.toshshahartransxizmat.toshbustravel.data.network

import uz.toshshahartransxizmat.toshbustravel.data.model.VehicleDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ActiveOrderDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.AddCardDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.CalculatorDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.CardsDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ClientDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.OrderDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.PaymentDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ResetDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.SignUpDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportDTO
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.AddCardEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CalculatorEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CreateOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.PayOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.UserProfileEntity

interface KtorService {

    suspend fun getVehicles(query: String, page: Int, size: Int): VehicleDTO

    suspend fun postSignUp(signUpEntity: SignUpEntity): SignUpDTO

    suspend fun postSignIn(signInEntity: SignInEntity):LogInDTO

    suspend fun postResetPassword(resetEntity: ResetEntity): ResetDTO

    suspend fun postPasswordVerify(resetEntity: ResetEntity): ResetDTO

    suspend fun getDetails(id: Int): TransportDTO

    suspend fun getClientInfo(): ClientDTO

    suspend fun postUpdateClient(userProfileEntity : UserProfileEntity): ClientDTO

    suspend fun postCreateOrder(createOrderEntity: CreateOrderEntity): OrderDTO

    suspend fun getActiveOrder(): ActiveOrderDTO

    suspend fun postPayOrder(payOrderEntity: PayOrderEntity): PaymentDTO

    suspend fun getOrders():OrderDTO

    suspend fun postCalculator(calculatorEntity: CalculatorEntity): CalculatorDTO

    suspend fun getCards(): CardsDTO

    suspend fun postAddCard(addCardEntity: AddCardEntity): AddCardDTO

    fun close()
}