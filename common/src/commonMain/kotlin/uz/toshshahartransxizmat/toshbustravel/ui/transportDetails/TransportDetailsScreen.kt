package uz.toshshahartransxizmat.toshbustravel.ui.transportDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.components.dialog.ErrorDialog
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeader
import uz.toshshahartransxizmat.toshbustravel.components.header.PageHeaderType
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component.SlideTransports
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component.TransportDetailsContent
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.viewModel.DetailsViewModel
import uz.toshshahartransxizmat.toshbustravel.util.Other

internal class TransportDetailsScreen(
    private val vehicleId:Int
): Screen {

    init {
        Other.isBottomBarVisible = false
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberKoinInject<DetailsViewModel>()
        val state = viewModel.state.collectAsState()
        var showErrorDialog by remember { mutableStateOf(true) }

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
                    onNavigationClick = {
                         //navigator.popUntilRoot()
                        navigator.pop()
                        Other.isBottomBarVisible = true
                    }
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
            ErrorDialog(
                errorMessage = state.value.error,
                showDialog = showErrorDialog,
                onDismiss = { showErrorDialog = false }
            )
        }
    }
}