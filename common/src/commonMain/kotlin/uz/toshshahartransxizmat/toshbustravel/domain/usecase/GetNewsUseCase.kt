package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.News
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias GetNewsBaseUseCase = BaseUseCase<String,Flow<List<News>>>

class GetNewsUseCase : KoinComponent, GetNewsBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: String): Flow<List<News>> {
        return repository.getNews(parameter)
    }
}