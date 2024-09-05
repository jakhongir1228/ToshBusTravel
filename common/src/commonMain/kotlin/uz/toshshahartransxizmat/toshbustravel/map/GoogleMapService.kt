package uz.toshshahartransxizmat.toshbustravel.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import uz.toshshahartransxizmat.toshbustravel.map.Location


expect class LocationProvider {
    suspend fun getCurrentLocation(): Location?
}


//expect class LocationProviderImpl() : LocationProvider

@Composable
expect fun ComposeMapView(locationProvider: LocationProvider, modifier: Modifier = Modifier)

data class Location(var latitude: Double, var longitude: Double)