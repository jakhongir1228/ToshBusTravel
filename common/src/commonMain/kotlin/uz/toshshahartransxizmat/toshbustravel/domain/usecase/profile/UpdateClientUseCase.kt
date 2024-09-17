package uz.toshshahartransxizmat.toshbustravel.domain.usecase.profile

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.UserProfileEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientUpdateData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias UpdateClientBaseUseCase = BaseUseCase<UserProfileEntity, Flow<ClientUpdateData>>

class UpdateClientUseCase: KoinComponent, UpdateClientBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: UserProfileEntity): Flow<ClientUpdateData> {
        return repository.postUpdateClient(parameter)
    }
}