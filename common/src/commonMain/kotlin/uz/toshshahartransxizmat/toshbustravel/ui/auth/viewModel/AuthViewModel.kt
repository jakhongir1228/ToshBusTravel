package uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.auth.state.AuthState

class AuthViewModel(
    private val useCases: AllUseCases
):ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state get() = _state.asStateFlow()

    init {
        println("@@@init")
    }

    fun loadAuth(signUpEntity: SignUpEntity){
        viewModelScope.launch{
            useCases.postSignUpUseCase(signUpEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("tttt->${t.message}")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Error has occurred",
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