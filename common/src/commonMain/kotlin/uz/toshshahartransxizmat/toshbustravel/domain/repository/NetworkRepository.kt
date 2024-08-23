package uz.toshshahartransxizmat.toshbustravel.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.toshshahartransxizmat.toshbustravel.domain.model.News

interface NetworkRepository {
    suspend fun getNews(query: String): Flow<List<News>>
}