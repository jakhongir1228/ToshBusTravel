package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataOrder(
    val content: List<ContentItem> = emptyList(),
    val totalCount: Int,
    val totalPages: Int,
    val page: Int
)
