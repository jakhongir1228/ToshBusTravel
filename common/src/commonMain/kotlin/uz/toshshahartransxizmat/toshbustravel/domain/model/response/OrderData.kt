package uz.toshshahartransxizmat.toshbustravel.domain.model.response

import uz.toshshahartransxizmat.toshbustravel.data.model.response.ContentItem

data class OrderData(
    val content: List<ContentItem>,
    val totalCount: Int,
    val totalPages: Int,
    val page: Int
)
