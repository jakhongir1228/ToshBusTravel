package uz.toshshahartransxizmat.toshbustravel.ui.amount.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CalculatorEntity
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.amount.state.AmountState

class AmountViewModel(
    private val useCases: AllUseCases
): ViewModel() {

    private val _state = MutableStateFlow(AmountState())
    val state get() = _state.asStateFlow()

    init {
        println("@@@init")
    }

    fun loadCalculator(calculatorEntity: CalculatorEntity){
        viewModelScope.launch {
            useCases.calculatorUseCase(calculatorEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("calculatorError-->> $t")
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = "amount not found",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            success = result.data,
                            isLoaded = true
                        )
                    }
                }
        }
    }

    fun resetLoadedState() {
        _state.update {
            it.copy(isLoaded = false)
        }
    }

    fun onAmountDialogDismissed() {
        _state.update {
            it.copy(isLoaded = false)
        }
    }

}