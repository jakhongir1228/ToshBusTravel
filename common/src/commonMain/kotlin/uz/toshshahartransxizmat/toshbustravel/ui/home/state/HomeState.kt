package uz.toshshahartransxizmat.toshbustravel.ui.home.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.News

data class HomeState(
    val success: List<List<News>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)