package uz.toshshahartransxizmat.toshbustravel.ui.transportDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.OrderCharts
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.TransportDetailsData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.VehicleDetails
import uz.toshshahartransxizmat.toshbustravel.share.getSettingsSource
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component.SlideTransports
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component.TransportDetailsContent
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.viewModel.DetailsViewModel
import uz.toshshahartransxizmat.toshbustravel.util.ACCESS_TOKEN_KEY

internal class TransportDetailsScreen(
    private val vehicleId:Int
): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val settings = remember { getSettingsSource() }
        settings.saveValue(ACCESS_TOKEN_KEY,"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlt7ImF1dGhvcml0eSI6IlJPTEVfQ0xJRU5UIn1dLCJ0eXAiOiJiZWFyZXIiLCJsYW5nIjoidXoiLCJkZXZpY2VJZCI6IjY3NjciLCJzdWIiOiI5OTg5OTkyNTE1MTkiLCJpYXQiOjE3MjYyMDI1MjAsImV4cCI6MTcyOTgwMjUyMH0.nPg91MYxyHmorJnHhJHc7K59uZWv23ClWjdEC9yhrLc")
        val viewModel = rememberKoinInject<DetailsViewModel>()
        val state = viewModel.state.collectAsState()

        LaunchedEffect(vehicleId) {
            viewModel.loadDetails(vehicleId)
        }

        Scaffold {
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

                if (state.value.isLoaded){
                    TransportDetailsContent(
                        transportDetailsData = state.value.success.data,
                        navigator = navigator,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }



            }
        }
        if (state.value.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Text(text = state.value.error, fontSize = 25.sp)
            }
        }
    }
}