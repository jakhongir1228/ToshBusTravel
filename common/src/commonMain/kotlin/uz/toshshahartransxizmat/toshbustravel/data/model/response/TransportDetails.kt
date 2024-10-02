package uz.toshshahartransxizmat.toshbustravel.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TransportDetails(
    val vehicleId: Int,
    val busTypeId: Int,
    val modelName: String,
    val passengerCapacity: Int,
    val vehicleDetails: List<VehicleDetail> = emptyList(),
    val orderCharts: List<OrderChart> = emptyList()
)
