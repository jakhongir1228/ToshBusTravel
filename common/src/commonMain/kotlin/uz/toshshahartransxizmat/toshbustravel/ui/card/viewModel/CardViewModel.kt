package uz.toshshahartransxizmat.toshbustravel.ui.card.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.AddCardEntity
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.card.state.CardState

class CardViewModel(
    private val useCases: AllUseCases
): ViewModel() {

    private val _state = MutableStateFlow(CardState())
    val state get() = _state.asStateFlow()

    init {
        println("@@@init")
    }

    fun loadGetCards(){
        viewModelScope.launch {
            useCases.getCardsUseCase()
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("getCardError-->> $t")
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
                            cards = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }

    fun loadAddCard(addCardEntity: AddCardEntity){
        viewModelScope.launch {
            useCases.addCardUseCase(addCardEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("addCardError-->> $t")
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = "Нет доступа",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            addCardData = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }
}