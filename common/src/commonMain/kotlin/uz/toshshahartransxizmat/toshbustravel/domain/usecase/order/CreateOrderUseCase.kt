package uz.toshshahartransxizmat.toshbustravel.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CreateOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias CreateOrderBaseUseCase = BaseUseCase<CreateOrderEntity, Flow<OrderResponseData>>

class CreateOrderUseCase: KoinComponent,CreateOrderBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: CreateOrderEntity): Flow<OrderResponseData> {
        return repository.postCreateOrder(parameter)
    }
}