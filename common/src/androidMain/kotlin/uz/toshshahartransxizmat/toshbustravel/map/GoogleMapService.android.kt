package uz.toshshahartransxizmat.toshbustravel.map

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch

@Composable
actual fun ComposeMapView(
    locationProvider: LocationProvider,
    modifier: Modifier
) {
    val context = LocalContext.current
    var location by remember { mutableStateOf<Location?>(null) }
    val cameraPositionState = rememberCameraPositionState()
    val coroutineScope = rememberCoroutineScope()
    var markerState= rememberMarkerState()
    LaunchedEffect(locationProvider) {
        coroutineScope.launch {
            try {
                location = locationProvider.getCurrentLocation()
                markerState= MarkerState( position = LatLng(location!!.latitude,location!!.longitude))
                Log.i("mapLocation", "Latitude: ${location?.latitude}, Longitude: ${location?.longitude}")
                location?.let {
                    cameraPositionState.position = CameraPosition(
                      LatLng(it.latitude, it.longitude), 15f,0f,0f
                    )
                }
            } catch (e: Exception) {
                Log.e("mapLocation", "Error fetching location", e)
            }
        }
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        location?.let {
            Marker(
                state = markerState,
                title = "Current Location"
            )
        }
    }
}