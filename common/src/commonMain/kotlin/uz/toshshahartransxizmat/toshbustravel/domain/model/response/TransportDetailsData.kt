package uz.toshshahartransxizmat.toshbustravel.domain.model.response

data class TransportDetailsData(
    val vehicleId: Int,
    val modelName: String,
    val passengerCapacity: Int,
    val vehicleDetails: List<VehicleDetails>,
    val orderCharts: List<OrderCharts>
)
