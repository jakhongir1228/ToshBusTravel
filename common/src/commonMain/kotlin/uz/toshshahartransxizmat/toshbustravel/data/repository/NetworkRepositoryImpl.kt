package uz.toshshahartransxizmat.toshbustravel.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toSignInData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toSignUpData
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toTransports
import uz.toshshahartransxizmat.toshbustravel.data.network.KtorService
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignInData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignUpData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val ktorService: KtorService
): NetworkRepository {
    override suspend fun getVehicles(query: String, page: Int, size: Int): Flow<List<Transports>> = flow {
        val r = ktorService.getVehicles(query,page,size)
        emit(r.content.map { it.toTransports() })
    }

    override suspend fun postSignUp(signUp: SignUpEntity): Flow<SignUpData> = flow {
        val r = ktorService.postSignUp(signUp)
        emit(r.data.toSignUpData())
    }

    override suspend fun postSignIn(signInEntity: SignInEntity): Flow<SignInData> = flow {
        val r = ktorService.postSignIn(signInEntity)
        emit(r.data.toSignInData())
    }
}