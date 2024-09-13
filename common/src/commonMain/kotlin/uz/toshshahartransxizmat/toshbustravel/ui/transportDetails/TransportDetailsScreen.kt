package uz.toshshahartransxizmat.toshbustravel.ui.transportDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderCharts
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.TransportDetailsData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.VehicleDetails
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component.SlideTransports
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component.TransportDetailsContent

internal class TransportDetailsScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PageHeader(
                type = PageHeaderType.Heading(text = ""),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                onNavigationClick = { navigator.pop() }
            )

            SlideTransports(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(272.dp)
            )

            val transportDetailsData = TransportDetailsData(
                vehicleId = 1,
                modelName = "Mercedes",
                passengerCapacity = 40,
                vehicleDetails = listOf(
                    VehicleDetails(
                        id = 2,
                        name = "Кондиционер",
                        iconPath = "CONTIDIONER_TYPE"
                    ),
                    VehicleDetails(
                        id = 1,
                        name = "Монитор",
                        iconPath = "MONITOR_TYPE"
                    ),
                    VehicleDetails(
                        id = 1,
                        name = "USB порт",
                        iconPath = "USB_TYPE"
                    )
                ),
                orderCharts = listOf(
                    OrderCharts(
                        id = 1,
                        orderStart = "2024-08-30T10:00:00",
                        orderEnd = "2024-08-30T12:00:00"
                    ),
                    OrderCharts(
                        id = 2,
                        orderStart = "2024-08-30T14:00:00",
                        orderEnd = "2024-08-30T16:00:00"
                    ),
                    OrderCharts(
                        id = 1,
                        orderStart = "2024-08-30T10:00:00",
                        orderEnd = "2024-08-30T12:00:00"
                    ),
                    OrderCharts(
                        id = 2,
                        orderStart = "2024-08-30T14:00:00",
                        orderEnd = "2024-08-30T16:00:00"
                    ),
                    OrderCharts(
                        id = 1,
                        orderStart = "2024-08-30T10:00:00",
                        orderEnd = "2024-08-30T12:00:00"
                    ),
                    OrderCharts(
                        id = 2,
                        orderStart = "2024-08-30T14:00:00",
                        orderEnd = "2024-08-30T16:00:00"
                    )
                )
            )

            TransportDetailsContent(
                transportDetailsData = transportDetailsData,
                navigator = navigator,
                modifier = Modifier.padding(top = 16.dp)
            )

        }
    }

}