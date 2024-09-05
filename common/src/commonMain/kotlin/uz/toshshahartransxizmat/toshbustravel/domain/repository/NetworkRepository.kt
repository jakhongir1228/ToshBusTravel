package uz.toshshahartransxizmat.toshbustravel.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.toshshahartransxizmat.toshbustravel.domain.model.News
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignInData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignUpData

interface NetworkRepository {
    suspend fun getNews(query: String): Flow<List<News>>

    suspend fun getTransports(query: String): Flow<List<Transports>>

    suspend fun postSignUp(signUp: SignUpEntity):Flow<SignUpData>

    suspend fun postSignIn(signInEntity: SignInEntity):Flow<SignInData>
}