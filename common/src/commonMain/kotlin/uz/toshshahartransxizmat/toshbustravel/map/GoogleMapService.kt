package uz.toshshahartransxizmat.toshbustravel.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import uz.toshshahartransxizmat.toshbustravel.map.Location


expect open class LocationProvider {
    suspend fun getCurrentLocation(): Location?
}

@Composable
expect fun ComposeMapView(vehicleId:Int,modifier: Modifier = Modifier)

data class Location(var latitude: Double, var longitude: Double)
