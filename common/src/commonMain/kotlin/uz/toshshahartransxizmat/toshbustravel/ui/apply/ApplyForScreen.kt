package uz.toshshahartransxizmat.toshbustravel.ui.apply

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import uz.toshshahartransxizmat.toshbustravel.map.ComposeMapView


//internal class ApplyForScreen(private val locationProvider: LocationProvider) : Screen {
internal class ApplyForScreen(val vehicleId:Int) : Screen {

    @Composable
    override fun Content() {
        val modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)

            ComposeMapView(
                modifier = modifier,
                vehicleId = vehicleId
            )

    }
}
