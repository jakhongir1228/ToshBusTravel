package uz.toshshahartransxizmat.toshbustravel.data.network

import uz.toshshahartransxizmat.toshbustravel.data.model.NewsDTO

interface KtorService {
    suspend fun getNews(query: String): NewsDTO
    fun close()
}