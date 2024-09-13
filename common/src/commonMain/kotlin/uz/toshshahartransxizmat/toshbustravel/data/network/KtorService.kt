package uz.toshshahartransxizmat.toshbustravel.data.network

import uz.toshshahartransxizmat.toshbustravel.data.model.VehicleDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity

interface KtorService {

    suspend fun getVehicles(query: String, page: Int, size: Int): VehicleDTO

    suspend fun postSignUp(signUpEntity: SignUpEntity):LogInDTO

    suspend fun postSignIn(signInEntity: SignInEntity):LogInDTO

    fun close()
}