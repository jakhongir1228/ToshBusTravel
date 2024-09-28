package uz.toshshahartransxizmat.toshbustravel.ui.apply

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.map.ComposeMapView
import uz.toshshahartransxizmat.toshbustravel.map.Location
import uz.toshshahartransxizmat.toshbustravel.map.LocationProvider

internal class ApplyForScreen(private val locationProvider: LocationProvider) : Screen {

    @Composable
    override fun Content() {
        val modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)

            ComposeMapView(
                locationProvider = locationProvider,
                modifier = modifier
            )

    }
}
