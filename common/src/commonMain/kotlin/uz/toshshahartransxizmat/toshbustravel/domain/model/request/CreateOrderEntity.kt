package uz.toshshahartransxizmat.toshbustravel.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderEntity(
    val vehicleId: Int,
    val directionsFrom: String,
    val directionsTo: String,
    val price: Int,
    val orderStatus: String,
    val aLongitude: String,       // Longitude of the starting point
    val aLatitude: String,        // Latitude of the starting point
    val bLongitude: String,       // Longitude of the destination
    val bLatitude: String,        // Latitude of the destination
    val orderDate: String,        // The date and time the order was placed (ISO 8601 format)
    val orderStarts: String,      // The date and time the order is expected to start (ISO 8601 format)
    val orderEnds: String,        // The date and time the order is expected to complete (ISO 8601 format)
    val from: String,             // Starting location address
    val to: String,               // Destination address
    val distance: Int,
    val travelTime: Int
)
