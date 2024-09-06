package uz.toshshahartransxizmat.toshbustravel.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VehicleDTO(
    val content: List<Vehicles> = emptyList(),
    val pageable: Pageable,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean,
    val size: Int,
    val number: Int,
    val sort: Sort,
    val numberOfElements: Int,
    val first: Boolean,
    val empty: Boolean
)