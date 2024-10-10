package uz.toshshahartransxizmat.toshbustravel.ui.orders.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.orders.state.ActiveState

class ActiveOrderViewModel(
    private val useCases: AllUseCases
):ViewModel() {
    private val _state = MutableStateFlow(ActiveState())
    val state get() = _state.asStateFlow()

    init {
        println("@@@init")
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
                            error = "Техническая ошибка, попробуйте позже",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            activeOrder = result,
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
}