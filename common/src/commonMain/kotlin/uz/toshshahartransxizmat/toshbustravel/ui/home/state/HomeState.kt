package uz.toshshahartransxizmat.toshbustravel.ui.home.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports

data class HomeState(
    val success: List<List<Transports>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)