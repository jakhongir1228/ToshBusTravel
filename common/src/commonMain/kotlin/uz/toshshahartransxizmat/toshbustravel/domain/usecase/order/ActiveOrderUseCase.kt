package uz.toshshahartransxizmat.toshbustravel.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias ActiveOrderBaseUseCase = BaseUseCase<Unit, Flow<OrderResponseData>>

class ActiveOrderUseCase: KoinComponent, ActiveOrderBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: Unit): Flow<OrderResponseData> {
        return repository.getActiveOrder()
    }

    suspend operator fun invoke(): Flow<OrderResponseData>{
        return invoke(Unit)
    }
}
