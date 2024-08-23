package uz.toshshahartransxizmat.toshbustravel.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface LocationProvider {
    suspend fun getCurrentLocation(): Location?
}

//expect class LocationProviderImpl() : LocationProvider

@Composable
expect fun ComposeMapView(locationProvider: LocationProvider, modifier: Modifier = Modifier)

data class Location(val latitude: Double, val longitude: Double)