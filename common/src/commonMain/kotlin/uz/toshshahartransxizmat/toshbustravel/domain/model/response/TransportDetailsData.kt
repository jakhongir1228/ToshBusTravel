package uz.toshshahartransxizmat.toshbustravel.domain.model.response

import kotlinx.serialization.Serializable
import uz.toshshahartransxizmat.toshbustravel.data.model.response.OrderChart
import uz.toshshahartransxizmat.toshbustravel.data.model.response.VehicleDetail

@Serializable
data class TransportDetailsData(
    val vehicleId: Int,
    val busTypeId: Int,
    val modelName: String,
    val passengerCapacity: Int,
    val vehicleDetails: List<VehicleDetail> = emptyList(),
    val orderCharts: List<OrderChart> = emptyList()
)
