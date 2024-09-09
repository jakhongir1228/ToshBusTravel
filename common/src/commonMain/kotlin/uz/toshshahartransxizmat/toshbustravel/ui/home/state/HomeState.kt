package uz.toshshahartransxizmat.toshbustravel.ui.home.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports

data class HomeState(
    val success: List<List<Transports>> = listOf(emptyList(), emptyList(), emptyList()), // Har bo'lim uchun bo'sh ro'yxatlar
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
