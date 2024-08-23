package uz.toshshahartransxizmat.toshbustravel.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import platform.CoreLocation.CLLocationCoordinate2D
import platform.MapKit.MKMapView
import platform.MapKit.MKAnnotationView

@Composable
actual fun ComposeMapView(
    locationProvider: LocationProvider,
    modifier: Modifier
) {
    var mapLocation by remember { mutableStateOf<Location?>(null) }

    LaunchedEffect(Unit) {
        mapLocation = locationProvider.getCurrentLocation()
    }

//    mapLocation?.let { location ->
//        val coordinate = CLLocationCoordinate2D(location.latitude, location.longitude)
//        val mapView = MKMapView().apply {
//            setRegion(coordinate, animated = true)
//            addAnnotation(MKAnnotation(coordinate))
//        }
//        mapView // You need to integrate it efficiently with SwiftUI.
//    } ?: run {
//        // Handle loading state
//    }
}

//actual class LocationProviderImpl actual constructor() : LocationProvider {
//    override suspend fun getCurrentLocation(): Location? {
//
//    }
//}