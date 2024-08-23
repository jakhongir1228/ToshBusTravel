package uz.toshshahartransxizmat.toshbustravel.map

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Composable
actual fun ComposeMapView(
    locationProvider: LocationProvider,
    modifier: Modifier
) {
    val context = LocalContext.current
    var mapLocation by remember { mutableStateOf<Location?>(null) }

    LaunchedEffect(Unit) {
        mapLocation = locationProvider.getCurrentLocation()
    }

    AndroidView(factory = {
        MapView(context).apply {
            onCreate(null)
            onResume()
        }
    }, modifier = modifier) { mapView ->
        mapView.getMapAsync { googleMap ->
            mapLocation?.let { location ->
                val latLng = LatLng(location.latitude, location.longitude)
                googleMap.addMarker(MarkerOptions().position(latLng).title("Current Location"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }
    }
}

//actual class LocationProviderImpl: LocationProvider {
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//
//    // Initializer block to set up the location client
//    fun initialize(context: Context) {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//    }
//
//    override suspend fun getCurrentLocation(): Location? {
//        return suspendCancellableCoroutine { cont ->
////            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
////                if (location != null) {
////                    cont.resume(Location(location.latitude, location.longitude))
////                } else {
////                    cont.resume(null)
////                }
////            }.addOnFailureListener { exception ->
////                cont.resumeWithException(exception)
////            }
//        }
//    }
//}
//
//// provide function to get instance with Context initialized
//fun provideLocationProvider(context: Context): LocationProviderImpl {
//    return LocationProviderImpl().apply { initialize(context) }
//}