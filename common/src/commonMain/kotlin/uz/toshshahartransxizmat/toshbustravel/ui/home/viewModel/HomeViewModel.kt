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

    private var allPage = 0
    private var busPage = 0
    private var miniBusPage = 0

    private var oldItemCount = 0

    init {
        println("@@@init")
        loadTransports(0)
    }

    fun loadTransports(index: Int, nextPage: Boolean = false) {
        when (index) {
            0 -> {
                if (allList.isEmpty() || (nextPage && allList.size > oldItemCount + 9)) {
                    val params = VehicleQueryParams(
                        query = "ALL",
                        page = if (nextPage) ++allPage else allPage,
                        size = 10
                    )
                    fetchTransports(allList, params)
                }
            }
            1 -> {
                if (busList.isEmpty() || (nextPage && busList.size > oldItemCount + 9)) {
                    val params = VehicleQueryParams(
                        query = "BUS",
                        page = if (nextPage) ++busPage else busPage,
                        size = 10
                    )
                    fetchTransports(busList, params)
                }
            }
            else -> {
                if (miniBusList.isEmpty() || (nextPage && miniBusList.size > oldItemCount + 9)) {
                    val params = VehicleQueryParams(
                        query = "MINI_BUS",
                        page = if (nextPage) ++miniBusPage else miniBusPage,
                        size = 10
                    )
                    fetchTransports(miniBusList, params)
                }
            }
        }
        oldItemCount = when (index) {
            0 -> allList.size
            1 -> busList.size
            else -> miniBusList.size
        }
    }

    private fun fetchTransports(vehicleList: MutableList<Transports>, query: VehicleQueryParams) {
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
                            error = "Техническая ошибка, попробуйте позже",
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