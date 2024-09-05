package uz.toshshahartransxizmat.toshbustravel.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import platform.CoreLocation.CLLocationCoordinate2DMake
import cocoapods.GoogleMaps.GMSCameraUpdate
import cocoapods.GoogleMaps.GMSMapStyle
import cocoapods.GoogleMaps.GMSMapView
import cocoapods.GoogleMaps.animateWithCameraUpdate

@Composable
actual fun ComposeMapView(
    locationProvider: LocationProvider,
    modifier: Modifier
) {
    // Create a `GMSMapView` instance and configure it
    val googleMapView = remember {
        GMSMapView().apply {
            setMyLocationEnabled(true)
            settings.setMyLocationButton(true)
            settings.setScrollGestures(true)
            settings.setZoomGestures(true)
            settings.setCompassButton(false)
            // Optionally, set map style
           /* this.setMapStyle(
                GMSMapStyle.styleWithJSONString(mapStyle1(), error = null)
            )*/
        }
    }

    // Launch effect to update the map based on location
    LaunchedEffect(locationProvider) {
        val location = locationProvider.getCurrentLocation()
        location?.let {
            googleMapView.animateWithCameraUpdate(
                GMSCameraUpdate.setTarget(
                    CLLocationCoordinate2DMake(it.latitude, it.longitude)
                )
            )
        }
    }

    // Use UIKitView to integrate GMSMapView into Compose
    UIKitView(
        factory = { googleMapView },
        modifier = modifier
    )
}
