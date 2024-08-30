package uz.toshshahartransxizmat.toshbustravel.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toNews
import uz.toshshahartransxizmat.toshbustravel.data.mapper.toSignUpData
import uz.toshshahartransxizmat.toshbustravel.data.network.KtorService
import uz.toshshahartransxizmat.toshbustravel.domain.model.News
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignUpData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val ktorService: KtorService
): NetworkRepository {
    override suspend fun getNews(query: String): Flow<List<News>> = flow {
        val r = ktorService.getNews(query)
        emit(r.articles.map { it.toNews() })
    }

    override suspend fun getTransports(query: String): Flow<List<Transports>> = flow {

    }

    override suspend fun postSignUp(signUp: SignUpEntity): Flow<SignUpData> = flow {
        val r = ktorService.postSignUp(signUp)
        println("tttt-->"+r.status)
        emit(r.data.toSignUpData())
    }
}