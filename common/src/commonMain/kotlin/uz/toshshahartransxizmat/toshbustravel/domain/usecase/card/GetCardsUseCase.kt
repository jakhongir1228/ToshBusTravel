package uz.toshshahartransxizmat.toshbustravel.domain.usecase.card

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.Cards
import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias GetCardsBaseUseCase = BaseUseCase<Unit, Flow<List<Cards>>>

class GetCardsUseCase: KoinComponent, GetCardsBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: Unit): Flow<List<Cards>> {
        return repository.getCards()
    }

    suspend operator fun invoke(): Flow<List<Cards>> {
        return invoke(Unit)
    }
}