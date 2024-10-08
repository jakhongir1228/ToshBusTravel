package uz.toshshahartransxizmat.toshbustravel.data.network

import uz.toshshahartransxizmat.toshbustravel.data.model.NewsDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportsDTO
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity

interface KtorService {

    suspend fun getNews(query: String): NewsDTO

    suspend fun postSignUp(signUpEntity: SignUpEntity):TransportsDTO

    suspend fun postSignIn(signInEntity: SignInEntity):LogInDTO

    fun close()
}