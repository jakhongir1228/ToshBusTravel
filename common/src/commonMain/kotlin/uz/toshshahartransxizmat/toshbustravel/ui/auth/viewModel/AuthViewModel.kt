package uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
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
                    println("signError-->> "+t)
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

    fun loadLoginIn(signInEntity: SignInEntity){
        viewModelScope.launch {
            useCases.postSignInUseCase(signInEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {tt->
                    println("loginError-->"+tt)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = it.success.message.toString(),
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

    fun loadResetPassword(resetEntity: ResetEntity){
        println("reqq---> "+resetEntity)
        viewModelScope.launch {
            useCases.resetPasswordUseCase(resetEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {tt->
                    println("resetError-->"+tt)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "User not found",
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    println("ress---> "+result)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            successReset = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }

    fun loadPasswordVerify(resetEntity: ResetEntity){
        viewModelScope.launch {
            useCases.passwordVerifyUseCase(resetEntity)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {tt->
                    println("verifyError-->"+tt)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = it.success.message.toString(),
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            successReset = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }
}
