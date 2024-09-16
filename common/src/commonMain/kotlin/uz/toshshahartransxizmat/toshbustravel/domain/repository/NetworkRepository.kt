package uz.toshshahartransxizmat.toshbustravel.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.DetailsResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ResetData

interface NetworkRepository {
    suspend fun getVehicles(query: String, page: Int, size: Int): Flow<List<Transports>>

    suspend fun postSignUp(signUp: SignUpEntity):Flow<AuthResponseData>

    suspend fun postSignIn(signInEntity: SignInEntity):Flow<AuthResponseData>

    suspend fun postResetPassword(resetEntity: ResetEntity): Flow<ResetData>

    suspend fun postPasswordVerify(resetEntity: ResetEntity): Flow<ResetData>

    suspend fun getDetails(id:Int): Flow<DetailsResponseData>
}