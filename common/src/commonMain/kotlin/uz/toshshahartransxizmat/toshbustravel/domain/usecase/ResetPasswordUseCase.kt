package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ResetData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias PostResetBaseUseCase = BaseUseCase<ResetEntity, Flow<ResetData>>

class ResetPasswordUseCase: KoinComponent, PostResetBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: ResetEntity): Flow<ResetData> {
        return repository.postResetPassword(parameter)
    }
}