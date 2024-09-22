package uz.toshshahartransxizmat.toshbustravel.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias GetOrdersBaseUseCase = BaseUseCase<Unit, Flow<List<Orders>>>

class GetOrdersUseCase: KoinComponent, GetOrdersBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: Unit): Flow<List<Orders>> {
        return repository.getOrders()
    }

    suspend operator fun invoke(): Flow<List<Orders>> {
        return invoke(Unit)
    }
}