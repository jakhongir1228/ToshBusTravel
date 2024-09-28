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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
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
import com.google.android.gms.maps.model.Marker
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
import org.json.JSONObject
import java.net.URL
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


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
    var firstSemgentRoad by remember { mutableStateOf<List<LatLng>>(emptyList()) }
    var secondSegmentRoad by remember { mutableStateOf<List<LatLng>>(emptyList()) }

    val markersState = remember { mutableStateOf(emptyList<MarkerOptions>()) }
    val parkingPoint=LatLng(41.3063483,69.350843)

    var distanceOfPoints by remember { mutableStateOf<Double?>(null) }

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

    LaunchedEffect(startLatLng, endLatLng) {
        // Only proceed if both startLatLng and endLatLng are non-null
        if (startLatLng != null && endLatLng != null) {
            coroutineScope.launch {
                distanceOfPoints=calculateTotalDistance(parkingPoint, startLatLng!!, endLatLng!!)
                markersState.value = listOf(
                    MarkerOptions()
                        .position(parkingPoint)
                        .title("Parking Point")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)),
                    MarkerOptions()
                        .position(startLatLng!!)
                        .title("Start Location")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)),
                    MarkerOptions()
                        .position(endLatLng!!)
                        .title("Destination Location")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                )

                // Clear road paths
                firstSemgentRoad = emptyList()
                secondSegmentRoad = emptyList()

                // Fetch and set road paths
                firstSemgentRoad = getRoadPath(parkingPoint, startLatLng!!)
                secondSegmentRoad = getRoadPath(startLatLng!!, endLatLng!!)

                // Update camera bounds to show both markers
                val bounds = LatLngBounds.Builder()
                    .include(startLatLng!!)
                    .include(endLatLng!!)
                    .build()
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngBounds(bounds, 100)
                )
            }
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        if (distanceOfPoints != null)
        {
            Text("Distance is $distanceOfPoints")
        }
        GoogleMap(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true)
        ) {
            location?.let {

                if (startLatLng != null && endLatLng != null) {


                    if (firstSemgentRoad.isNotEmpty()) {
                        Polyline(
                            points = firstSemgentRoad,
                            color = Color.Blue,
                            width = 8f
                        )
                    }
                    if (secondSegmentRoad.isNotEmpty()) {
                        Polyline(
                            points = secondSegmentRoad,
                            color = Color.Green,
                            width = 8f
                        )
                    }
                    markersState.value.forEach { markerOptions ->
                        key(markerOptions.position) {
                            Marker(
                                state = rememberMarkerState(position = markerOptions.position),
                                title = markerOptions.title,
                                icon = markerOptions.icon
                            )
                        }
                    }


               /*     Marker(
                        state = rememberMarkerState(position = startLatLng!!),
                        title = "Start Location" ,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN) // Set marker color to blue

                    )
                    Marker(
                        state = rememberMarkerState(position = endLatLng!!),
                        title = "Destination Location",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN) // Set marker color to blue

                    )*/

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

suspend fun getRoadPath(startLatLng: LatLng, endLatLng: LatLng): List<LatLng> {
    val url = "https://maps.googleapis.com/maps/api/directions/json?" +
            "origin=${startLatLng.latitude},${startLatLng.longitude}" +
            "&destination=${endLatLng.latitude},${endLatLng.longitude}" +
            "&key=${"AIzaSyAg2fOUJo8a-LuMvDYOccUBq9gDxoLZhmE"}"

    return withContext(Dispatchers.IO) {
        try {
            val result = URL(url).readText()
            val jsonObject = JSONObject(result)
            val routes = jsonObject.getJSONArray("routes")
            if (routes.length() > 0) {
                val route = routes.getJSONObject(0)
                val overviewPolyline = route.getJSONObject("overview_polyline")
                val encodedPolyline = overviewPolyline.getString("points")

                // Decode the polyline points
                return@withContext decodePolyline(encodedPolyline)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

// Polyline decoding function
fun decodePolyline(encoded: String): List<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dlat

        shift = 0
        result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dlng

        val p = LatLng(
            lat / 1E5,
            lng / 1E5
        )
        poly.add(p)
    }

    return poly
}
fun haversineDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val earthRadius = 6371e3 // Radius of the Earth in meters

    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)

    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2) * sin(dLon / 2)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadius * c // Distance in meters
}

fun calculateTotalDistance(latLng1: LatLng, latLng2: LatLng, latLng3: LatLng): Double {
    val distance1 = haversineDistance(latLng1.latitude, latLng1.longitude, latLng2.latitude, latLng2.longitude)
    val distance2 = haversineDistance(latLng2.latitude, latLng2.longitude, latLng3.latitude, latLng3.longitude)

    return distance1 + distance2 // Total distance in meters
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
