package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias GetTransportsBaseUseCase = BaseUseCase<String, Flow<List<Transports>>>

class GetTransportsUseCase: KoinComponent,GetTransportsBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: String): Flow<List<Transports>> {
        return repository.getTransports(parameter)
    }
}