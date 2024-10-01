package uz.toshshahartransxizmat.toshbustravel.domain.usecase.amount

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CalculatorEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.CalculatorResponse
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.BaseUseCase

typealias CalculatorBaseUseCase = BaseUseCase<CalculatorEntity, Flow<CalculatorResponse>>

class CalculatorUseCase: KoinComponent, CalculatorBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: CalculatorEntity): Flow<CalculatorResponse> {
        return repository.postCalculator(parameter)
    }
}