package uz.toshshahartransxizmat.toshbustravel.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.PayOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias PayOrderBaseUseCase = BaseUseCase<PayOrderEntity, Flow<OrderResponseData>>

class PayOrderUseCase: KoinComponent, PayOrderBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: PayOrderEntity): Flow<OrderResponseData> {
        return repository.postPayOrder(parameter)
    }
}
