package uz.toshshahartransxizmat.toshbustravel.map

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
actual fun ComposeMapView(
    locationProvider: LocationProvider,
    modifier: Modifier
) {
    val context = LocalContext.current
    var location by remember { mutableStateOf<Location?>(null) }
    val cameraPositionState = rememberCameraPositionState()
    val coroutineScope = rememberCoroutineScope()
    var startLatLng by remember { mutableStateOf<LatLng?>(null) }
    var endLatLng by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(locationProvider) {
        coroutineScope.launch {
            try {
                location = locationProvider.getCurrentLocation()
                Log.i("mapLocation", "Latitude: ${location?.latitude}, Longitude: ${location?.longitude}")
                location?.let {
                    cameraPositionState.position = CameraPosition(
                        LatLng(it.latitude, it.longitude), 15f, 0f, 0f
                    )
                }
            } catch (e: Exception) {
                Log.e("mapLocation", "Error fetching location", e)
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true)
        ) {
            val parkingPoint=LatLng(41.3063483,69.350843)
            location?.let {
                // Marker for current location
               /* Marker(
                    state = rememberMarkerState(position = LatLng(it.latitude, it.longitude)),
                    title = "Current Location"
                )*/

                // Draw polyline and markers if start and destination are set
                if (startLatLng != null && endLatLng != null) {
                    Polyline(
                        points = listOf(parkingPoint,startLatLng!!, endLatLng!!),
                        color = Color.Blue,
                        width = 5f
                    )
                    Marker(
                        state = rememberMarkerState(position = parkingPoint!!),
                        title = "Parking Point" ,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE) // Set marker color to blue

                    )
                    // Add markers for start and end locations
                    Marker(
                        state = rememberMarkerState(position = startLatLng!!),
                        title = "Start Location" ,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN) // Set marker color to blue

                    )
                    Marker(
                        state = rememberMarkerState(position = endLatLng!!),
                        title = "Destination Location",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN) // Set marker color to blue

                    )

                    LaunchedEffect(startLatLng, endLatLng) {
                        coroutineScope.launch {
                            val bounds = LatLngBounds.Builder()
                                .include(startLatLng!!)
                                .include(endLatLng!!)
                                .build()
                            cameraPositionState.move(
                                CameraUpdateFactory.newLatLngBounds(bounds, 100)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Design at the bottom
        DirectionSheetDesign(
            startLatLng = { startLatLng },
            endLatLng = { endLatLng },
            onUpdateStartEndPoints = { start, end ->
                coroutineScope.launch {
                    startLatLng = getLatLngFromAddress(context, start)
                    endLatLng = getLatLngFromAddress(context, end)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}


@Composable
fun DirectionSheetDesign(
    startLatLng: () -> LatLng?,
    endLatLng: () -> LatLng?,
    onUpdateStartEndPoints: (String, String) -> Unit,
    modifier: Modifier
) {
    var start by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var startError by remember { mutableStateOf<String?>(null) }
    var destinationError by remember { mutableStateOf<String?>(null) }

    val onValidateAndDrawPath: () -> Unit = {
        startError = if (start.isEmpty()) "Start location is empty" else null
        destinationError = if (destination.isEmpty()) "Destination is empty" else null

        if (startError == null && destinationError == null) {
            onUpdateStartEndPoints(start, destination)
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(text = "От куда")
                AddressTextField(
                    AddressText = start,
                    onTextChange = { newAddress ->
                        start = newAddress
                    },
                    modifier = Modifier.fillMaxWidth(),
                    errorText = startError,
                    hint = "Current Location"
                )

                Text(text = "куда")
                AddressTextField(
                    AddressText = destination,
                    onTextChange = { newAddress ->
                        destination = newAddress
                    },
                    modifier = Modifier.fillMaxWidth(),
                    errorText = destinationError,
                    hint = "Продолжить"
                )
            }
        }

        Button(
            onClick = { onValidateAndDrawPath() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .height(50.dp)
        ) {
            Text(text = "Продолжить")
        }
    }
}
@Composable
fun DrawMapWithPath(googleMap: GoogleMap, start: String, destination: String) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var startLatLng by remember { mutableStateOf<LatLng?>(null) }
    var endLatLng by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(start, destination) {
        coroutineScope.launch {
            // Get LatLng for start and destination addresses
            startLatLng = getLatLngFromAddress(context, start)
            endLatLng = getLatLngFromAddress(context, destination)

            if (startLatLng != null && endLatLng != null) {
                // Add markers for both locations
                googleMap.addMarker(MarkerOptions().position(startLatLng!!).title("Start Location"))
                googleMap.addMarker(MarkerOptions().position(endLatLng!!).title("Destination Location"))

                // Add a path (polyline) between the two points
                googleMap.addPolyline(
                    PolylineOptions()
                        .add(startLatLng, endLatLng)
                        .width(5f)
                        .color(android.graphics.Color.BLUE) // Color of the polyline
                )

                // Adjust the camera to fit both points
                val bounds = LatLngBounds.Builder()
                    .include(startLatLng!!)
                    .include(endLatLng!!)
                    .build()

                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
            }
        }
    }
}

// Helper function to get LatLng from an address
suspend fun getLatLngFromAddress(context: Context, address: String): LatLng? {
    return withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context)
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses!!.isNotEmpty()) {
                val location = addresses[0]
                LatLng(location.latitude, location.longitude)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

/*
@Preview
@Composable
fun PreviewComposeMapView() {
    val mockLocationProvider = object : LocationProvider(LocalContext.current) {
        override suspend fun getCurrentLocation(): Location {
            return Location(37.7749, -122.4194) // Hardcoded location for preview
        }
    }

    ComposeMapView(
        locationProvider = mockLocationProvider,
        modifier = Modifier.fillMaxSize()
    )
}*/
