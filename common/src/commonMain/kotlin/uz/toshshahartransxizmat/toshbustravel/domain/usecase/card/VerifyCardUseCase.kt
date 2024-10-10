package uz.toshshahartransxizmat.toshbustravel.domain.usecase.card

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.Cards
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.VerifyCardEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.VerifyCardData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias VerifyCardBaseUseCase = BaseUseCase<VerifyCardEntity, Flow<VerifyCardData>>

class VerifyCardUseCase: KoinComponent, VerifyCardBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: VerifyCardEntity): Flow<VerifyCardData> {
        return repository.postVerifyCard(parameter)
    }
}