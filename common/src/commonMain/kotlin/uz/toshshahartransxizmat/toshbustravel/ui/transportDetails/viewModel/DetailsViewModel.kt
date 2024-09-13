package uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.state.TransportDetailsState

class DetailsViewModel(
    private val useCases: AllUseCases
): ViewModel() {
    private val _state = MutableStateFlow(TransportDetailsState())
    val state get() = _state.asStateFlow()

    init {
        println("@@@init")
    }

    fun loadDetails(id: Int){
        viewModelScope.launch {
            useCases.getDetailsUseCase(id)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("eerrr-->> "+t)
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = res.success.message.toString(),
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            success = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }
}