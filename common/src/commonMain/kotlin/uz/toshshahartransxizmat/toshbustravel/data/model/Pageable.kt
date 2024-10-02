package uz.toshshahartransxizmat.toshbustravel.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Pageable(
    val pageNumber: Int,
    val pageSize: Int,
    val sort: Sort,
    val offset:Int,
    val unpaged:Boolean,
    val paged:Boolean
)