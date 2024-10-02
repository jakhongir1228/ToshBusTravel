package uz.toshshahartransxizmat.toshbustravel.ui.profile.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.UserProfileEntity
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.profile.state.ProfileState

class ProfileViewModel (
    private val useCases: AllUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state get() = _state.asStateFlow()

    init {
        println("@@@init")
       // loadGetClient()
    }

    fun loadGetClient(){
        viewModelScope.launch {
            useCases.getClientUseCase()
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {t->
                    println("signError-->> $t")
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = "User not found",
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

    fun loadUpdateClient(userProfile: UserProfileEntity) {
        viewModelScope.launch {
            useCases.updateClientUseCase(userProfile)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch {tt->
                    println("signError-->> ${tt.message}")
                    _state.update { res->
                        res.copy(
                            isLoading = false,
                            error = tt.message.toString(),
                            isLoaded = false
                        )
                    }
                }.collectLatest { result->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            successUpdate = result,
                            isLoaded = true
                        )
                    }
                }
        }
    }
}