package uz.toshshahartransxizmat.toshbustravel.domain.usecase.profile

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias GetClientBaseUseCase = BaseUseCase<Unit, Flow<ClientData>>

class GetClientUseCase: KoinComponent, GetClientBaseUseCase{

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: Unit): Flow<ClientData> {
        return repository.getClientInfo()
    }

    suspend operator fun invoke(): Flow<ClientData> {
        return invoke(Unit)
    }
}