package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.DetailsResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias GetDetailsBaseUseCase = BaseUseCase<Int, Flow<DetailsResponseData>>

class GetDetailsUseCase: KoinComponent, GetDetailsBaseUseCase{

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: Int): Flow<DetailsResponseData> {
        return repository.getDetails(parameter)
    }

}