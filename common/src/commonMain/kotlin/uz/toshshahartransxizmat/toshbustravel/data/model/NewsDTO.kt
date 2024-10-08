package uz.toshshahartransxizmat.toshbustravel.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsDTO(
    val articles: List<Article> = emptyList(),
    val status: String,
    val totalResults: Int = 0
)