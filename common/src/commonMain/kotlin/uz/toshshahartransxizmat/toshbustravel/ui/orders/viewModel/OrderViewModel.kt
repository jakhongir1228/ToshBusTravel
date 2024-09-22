package uz.toshshahartransxizmat.toshbustravel.ui.orders.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CreateOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.PayOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.orders.state.OrderState

class OrderViewModel(
    private val useCases: AllUseCases
):ViewModel() {

    private val _state = MutableStateFlow(OrderState())
    val state get() = _state.asStateFlow()

    init {
        println("@@@init")
    }

    fun loadCreateOrder(createOrderEntity: CreateOrderEntity){
        viewModelScope.launch {
            useCases.createOrderUseCase(createOrderEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("createOrderError-->> $t")
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = "Serverda xatolik yuz berdi",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            orders = emptyList(),
                            isLoaded = true
                        )
                    }
                }
        }
    }

    fun loadActiveOrder(){
        viewModelScope.launch {
            useCases.activeOrderUseCase()
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
                            error = "Serverda xatolik yuz berdi",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            orders = emptyList(),
                            isLoaded = true
                        )
                    }
                }
        }
    }

    fun loadPayOrder(payOrderEntity: PayOrderEntity){
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
                            error = "Serverda xatolik yuz berdi",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            orders = emptyList(),
                            isLoaded = true
                        )
                    }
                }
        }
    }

    fun loadGetOrder(){
        viewModelScope.launch {
            useCases.getOrdersUseCase()
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("getOrderError-->> $t")
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = "Serverda xatolik yuz berdi",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            orders = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }
}