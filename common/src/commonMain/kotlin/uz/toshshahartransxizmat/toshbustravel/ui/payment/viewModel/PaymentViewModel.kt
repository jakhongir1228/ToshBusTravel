package uz.toshshahartransxizmat.toshbustravel.ui.payment.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.PayOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.payment.state.PaymentState
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

class PaymentViewModel(
    private val useCases: AllUseCases
):ViewModel() {
    private val _state = MutableStateFlow(PaymentState())
    val state get() = _state.asStateFlow()


    fun loadPayment(payOrderEntity: PayOrderEntity){
        viewModelScope.launch {
            useCases.payOrderUseCase(payOrderEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("activeOrderError-->> $t")
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = "Техническая ошибка, попробуйте позже",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            payment = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }

}