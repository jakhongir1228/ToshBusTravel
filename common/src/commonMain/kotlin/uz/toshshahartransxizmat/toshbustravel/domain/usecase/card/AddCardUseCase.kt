package uz.toshshahartransxizmat.toshbustravel.domain.usecase.card

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.AddCardEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AddCardData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias AddCardBaseUseCase = BaseUseCase<AddCardEntity, Flow<AddCardData>>

class AddCardUseCase: KoinComponent, AddCardBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: AddCardEntity): Flow<AddCardData> {
        return repository.postAddCard(parameter)
    }
}