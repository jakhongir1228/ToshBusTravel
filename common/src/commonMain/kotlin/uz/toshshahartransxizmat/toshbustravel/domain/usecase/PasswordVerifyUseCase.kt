package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ResetData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias PasswordVerifyBaseUseCase = BaseUseCase<ResetEntity, Flow<ResetData>>

class PasswordVerifyUseCase: KoinComponent, PasswordVerifyBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: ResetEntity): Flow<ResetData> {
        return repository.postPasswordVerify(parameter)
    }

}
