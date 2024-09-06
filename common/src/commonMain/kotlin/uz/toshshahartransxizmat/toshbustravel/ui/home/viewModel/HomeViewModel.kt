package uz.toshshahartransxizmat.toshbustravel.ui.home.viewModel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.VehicleQueryParams
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.ui.home.state.HomeState

class HomeViewModel(
    private val allUseCases: AllUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    private val allList = mutableListOf<Transports>()
    private val busList = mutableListOf<Transports>()
    private val miniBusList = mutableListOf<Transports>()

    init {
        println("@@@init")
        loadTransports(0)
    }

     fun loadTransports(index: Int) {
        when (index) {
            0 -> {
                if (allList.isEmpty()) {
                    val params= VehicleQueryParams(
                        query = "ALL",
                        page = 0,
                        size = 10
                    )
                    fetchNews(allList, params)
                }
            }

            1 -> {
                if (busList.isEmpty()) {
                    val params= VehicleQueryParams(
                        query = "BUS",
                        page = 0,
                        size = 10
                    )
                    fetchNews(busList, params)
                }
            }

            else -> {
                if (miniBusList.isEmpty()) {
                    val params= VehicleQueryParams(
                        query = "MINI_BUS",
                        page = 0,
                        size = 10
                    )
                    fetchNews(miniBusList, params)
                }
            }
        }
    }

    private fun fetchNews(vehicleList: MutableList<Transports>, query: VehicleQueryParams) {
        viewModelScope.launch {
            allUseCases.getTransportsUseCase(query)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch { t ->
                    println("@@@--->${t.message}")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Error has occurred",
                            isLoaded = false
                        )
                    }
                }.collectLatest { list ->

                    vehicleList.addAll(list)

                    _state.update {
                        it.copy(
                            isLoading = false,
                            success = listOf(allList, busList, miniBusList),
                            isLoaded = true
                        )
                    }
                }
        }
    }
}