package uz.toshshahartransxizmat.toshbustravel.map

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView

import cocoapods.GoogleMaps.GMSMapView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cocoapods.GoogleMaps.GMSCameraPosition
import cocoapods.GoogleMaps.GMSMarker
import cocoapods.GoogleMaps.GMSMutablePath
import cocoapods.GoogleMaps.GMSPolyline
import cocoapods.GoogleMaps.animateToCameraPosition
import cocoapods.GoogleMaps.kGMSTypeNormal
import io.ktor.client.HttpClient
import kotlinx.cinterop.CValue
import kotlinx.cinterop.useContents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.CoreLocation.CLLocationCoordinate2D
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.UIKit.UIColor
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLPlacemark
import uz.toshshahartransxizmat.toshbustravel.ui.amount.SeeAmountScreen
import uz.toshshahartransxizmat.toshbustravel.util.getStrings
import platform.CoreLocation.CLLocation
import platform.darwin.NSObject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.cinterop.ObjCAction
import kotlin.math.PI

@Composable
actual fun ComposeMapView(
    vehicleId: Int,
    modifier: Modifier
) {
    // State for start and end locations
    var startLatLng by remember { mutableStateOf<CLLocationCoordinate2D?>(null) }
    var endLatLng by remember { mutableStateOf<CLLocationCoordinate2D?>(null) }
    var distanceOfPoints by remember { mutableStateOf<Double?>(null) }
    val parkingPoint = CLLocationCoordinate2DMake(41.3063483, 69.350843) // Parking Point
    val coroutineScope = rememberCoroutineScope()

    // `UIKitView` for embedding Google Maps (`GMSMapView`)


    Column(modifier = modifier.fillMaxSize()) {
        // Display the distance between points
        if (distanceOfPoints != null) {
            Text(text = "Distance is ${distanceOfPoints} KM", modifier = Modifier.padding(16.dp))
        }
        UIKitView(
            modifier = modifier.fillMaxWidth().weight(1f),
            factory = {
                GMSMapView().apply {
                    mapType = kGMSTypeNormal
                    setMyLocationEnabled(true)
                    parkingPoint.useContents {
                        val camera = GMSCameraPosition.cameraWithLatitude(
                            latitude,
                            longitude,
                            15.0f
                        )
                        animateToCameraPosition(camera)
                    }

                }
            },
            update = { mapView ->
                startLatLng?.let { start ->
                    endLatLng?.let { end ->
                        // Add markers for start and end points
                        mapView.clear()

                        addMarkerOnMap(mapView, parkingPoint, "Parking Point", UIColor.orangeColor())
                        addMarkerOnMap(mapView, CLLocationCoordinate2DMake(start.latitude,start.longitude), "Start Location", UIColor.greenColor())
                        addMarkerOnMap(mapView, CLLocationCoordinate2DMake(end.latitude,end.longitude), "Destination", UIColor.cyanColor())

                        // Draw polyline between points
                        coroutineScope.launch {

                            /*     parkingPoint.useContents {
                                     val firstSegment = getRoadPath(this, start)
                                 }*/

                            val secondSegment = getRoadPath(start, end)

                            /* withContext(Dispatchers.Main) {
                                 parkingPoint.useContents {
                                     drawPolyline(mapView, getRoadPath(this,end), UIColor.blueColor())

                                 }
                                 drawPolyline(mapView, secondSegment, UIColor.greenColor())

                                 // Calculate distance
                                 distanceOfPoints = calculateTotalDistance(parkingPoint, start, end)

                                 // Adjust camera bounds
                                 val bounds = GMSCoordinateBounds(parkingPoint, end)
                                 mapView.animateWithCameraUpdate(GMSCameraUpdate.fitBounds(bounds, 100.0))
                             }*/
                        }
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Sheet to input locations
        DirectionSheetDesign(
            startLatLng = startLatLng,
            endLatLng = endLatLng,
            onUpdateStartEndPoints = { start, end ->
                coroutineScope.launch {
                    startLatLng = getLatLngFromAddress(start)
                    endLatLng = getLatLngFromAddress(end)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            vehicleId = vehicleId,
            parkingPoint = parkingPoint.useContents { this },
            distanceOfPoints = distanceOfPoints ?: 0.0
        )
    }




// Haversine distance calculation function
fun toRadians(degrees: Double): Double {
    return degrees * (PI / 180)
}

    fun haversineDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371e3 // Radius of the Earth in meters
        val dLat = toRadians(lat2 - lat1)
        val dLon = toRadians(lon2 - lon1)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(toRadians(lat1)) * cos(toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        val distance = earthRadius * c // distance in meters
        return distance / 1000 // convert to kilometers
    }

// Calculate the total distance of the journey (parking point -> start -> end)
fun calculateTotalDistance(
    parkingPoint: CLLocationCoordinate2D,
    startPoint: CLLocationCoordinate2D,
    endPoint: CLLocationCoordinate2D
): Double {
    val parkingToStart = haversineDistance(
        parkingPoint.latitude, parkingPoint.longitude,
        startPoint.latitude, startPoint.longitude
    )
    val startToEnd = haversineDistance(
        startPoint.latitude, startPoint.longitude,
        endPoint.latitude, endPoint.longitude
    )
    return parkingToStart + startToEnd
}

}

suspend fun getLatLngFromAddress(address: String): CLLocationCoordinate2D? {
    return suspendCancellableCoroutine { continuation ->
        val geocoder = CLGeocoder()
        geocoder.geocodeAddressString(address) { placemarks, error ->
            if (error != null) {
                continuation.resumeWithException(Throwable(error.localizedDescription))

            } else if (placemarks != null && placemarks.isNotEmpty()) {
                val placemark = placemarks.first() as? CLPlacemark

                // Use placemark's location to get coordinates
                val location: CLLocation? = placemark?.location
                if (location != null) {
                    val coordinate = location.coordinate
                    coordinate.useContents {
                        continuation.resume(this)
                    }
                } else {
                    continuation.resume(null)
                }
            } else {
                continuation.resume(null)
            }
        }
    }
}


// Helper function to add marker
fun addMarkerOnMap(mapView: GMSMapView, position: CValue<CLLocationCoordinate2D>, title: String, color: UIColor) {
    val marker = GMSMarker()
    marker.position = position
    marker.title = title
    marker.icon = GMSMarker.markerImageWithColor(color)
    marker.map = mapView
}

// Helper function to draw polyline on the map
fun drawPolyline(mapView: GMSMapView, path: List<CValue<CLLocationCoordinate2D>>, color: UIColor) {
    val polyline = GMSPolyline()
    polyline.strokeColor = color
    polyline.strokeWidth = 5.0
    polyline.path = GMSMutablePath().apply {
        path.forEach { coordinate ->
            addCoordinate(coordinate)
        }
    }
    polyline.map = mapView
}

fun CLLocationCoordinate2D.toQueryString(): String {
    return "${this.latitude},${this.longitude}"
}


suspend fun getRoadPath(startLatLng: CLLocationCoordinate2D, endLatLng: CLLocationCoordinate2D): List<CLLocationCoordinate2D> {
    val client = HttpClient {
        install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }) // Use Kotlinx JSON serialization
        }
    }


    val url = "https://maps.googleapis.com/maps/api/directions/json?" +
            "origin=${startLatLng.toQueryString()}" +
            "&destination=${endLatLng.toQueryString()}" +
            "&key=YOUR_API_KEY" // Replace with your actual API Key

    return withContext(Dispatchers.IO) {
        try{
        // Perform the GET request and get HttpResponse
        val response: String = client.get(url).toString()
        client.close() // Close the client after the request

        // Parse the response string into a JsonObject
        val jsonResponse = Json.parseToJsonElement(response).jsonObject

        // Extract routes array
        val routes = jsonResponse["routes"]?.jsonArray

        if (routes != null && routes.isNotEmpty()) {
            // Get the first route's overview_polyline and decode it
            val route = routes[0].jsonObject
            val overviewPolyline = route["overview_polyline"]?.jsonObject
            val encodedPolyline = overviewPolyline?.get("points")?.jsonPrimitive?.content

            if (encodedPolyline != null) {
                println("Encoded polyline: $encodedPolyline")

                // Decode the polyline points
                decodePolyline(encodedPolyline)
            } else {
                println("No polyline found")
                emptyList()
            }
        } else {
            println("No routes found")
            emptyList()
        }
    }
        catch (e: Exception) {
            println("Error fetching road path: ${e.message}")
            emptyList()
        }
    }
}
fun decodePolyline(encoded: String): List<CLLocationCoordinate2D> {
    val poly = mutableListOf<CLLocationCoordinate2D>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0

        // Decode latitude
        do {
            b = encoded[index++].code - 63
            result = result or ((b and 0x1f) shl shift)
            shift += 5
        } while (b >= 0x20)

        val dlat = if ((result and 1) != 0) -(result shr 1) else result shr 1

        lat += dlat

        shift = 0
        result = 0

        // Decode longitude
        do {
            b = encoded[index++].code - 63
            result = result or ((b and 0x1f) shl shift)
            shift += 5
        } while (b >= 0x20)

        val dlng = if (result and 1 != 0) -(result shr 1) else result shr 1
        lng += dlng

        // Create CLLocationCoordinate2D object
        val coord = CLLocationCoordinate2DMake(lat / 1E5, lng / 1E5).useContents { this }
        poly.add(coord)
    }

    return poly
}
@Composable
fun DirectionSheetDesign(
    startLatLng: CLLocationCoordinate2D?,
    endLatLng: CLLocationCoordinate2D?,
    onUpdateStartEndPoints: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    vehicleId: Int,
    parkingPoint: CLLocationCoordinate2D,
    distanceOfPoints: Double
) {
    var startAddress by remember { mutableStateOf("") }
    var endAddress by remember { mutableStateOf("") }

    var startError by remember { mutableStateOf<String?>(null) }
    var destinationError by remember { mutableStateOf<String?>(null) }

    val areBothFieldsFilled by remember {
        derivedStateOf { startAddress.isNotEmpty() && endAddress.isNotEmpty() }
    }
    val navigator = LocalNavigator.currentOrThrow

    val onValidateAndDrawPath: () -> Unit = {
        startError = if (startAddress.isEmpty()) "Start location is empty" else null
        destinationError = if (endAddress.isEmpty()) "Destination is empty" else null

        if (startError == null && destinationError == null) {
            onUpdateStartEndPoints(startAddress, endAddress)
        }
    }

    DisposableEffect(areBothFieldsFilled) {
        if (areBothFieldsFilled) {
            onValidateAndDrawPath()
            println("test----->")
        }
        onDispose { }
    }
    Column(modifier = modifier.padding(16.dp)) {
        ParkingTextField(
            hint = getStrings("Current Location"),
            addressText = startAddress,
            onTextChange = { newAddress ->
                startAddress = newAddress
            },
            modifier = Modifier.fillMaxWidth(),
            errorText = "startError")
        Spacer(modifier = Modifier.height(8.dp))
        ParkingTextField(
            hint = getStrings("Destination Location"),
            addressText = endAddress,
            onTextChange = { newAddress ->
                endAddress = newAddress
            },
            modifier = Modifier.fillMaxWidth(),
            errorText = "DestinationError")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(bottom = 42.dp),
            text = TextValue(getStrings("continue")),
            size = ButtonSize.Large,
            enabled = areBothFieldsFilled,
            onClick = {
                navigator.push(
                    SeeAmountScreen(vehicleId= vehicleId,
                        from= startAddress,
                        to= endAddress,
                        aLatitude=parkingPoint.latitude,
                        aLongitude= parkingPoint.longitude,
                        bLatitude=startLatLng!!.latitude,
                        bLongitude=startLatLng.longitude,
                        cLatitude=endLatLng!!.latitude,
                        cLongitude=endLatLng.longitude,
                        distanceofPoints=distanceOfPoints
                    )
                )

            }
        )


        Spacer(modifier = Modifier.height(16.dp))

    }
}
@Composable
fun ParkingTextField(
    hint: String,
    addressText: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorText: String?
) {
    val geocoder = remember { CLGeocoder() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                // Trigger geocoding when the box is clicked
                geocodeAddress(geocoder, addressText, onTextChange)
            }
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = addressText,
            onValueChange = { text -> onTextChange(text) },
            placeholder = {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = hint,
                    color = Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            textStyle = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            enabled = true
        )
    }

    if (!errorText.isNullOrEmpty()) {
        Text(
            text = errorText,
            color = Color.Red,
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 40.dp, top = 4.dp)
        )
    }
}
fun geocodeAddress(geocoder: CLGeocoder, addressText: String, onResult: (String) -> Unit) {
    if (addressText.isNotEmpty()) {
        geocoder.geocodeAddressString(addressText) { placemarks, error ->
            if (error != null) {
                println("Geocoding error: ${error.localizedDescription}")
                onResult("Error: Could not find the location.")
            } else if (placemarks != null && placemarks.isNotEmpty()) {
                val placemark: CLPlacemark = placemarks[0] as CLPlacemark
                if (placemark.locality != null) {
                    onResult(placemark.locality!!)
                } else {
                    onResult("Location found, but no locality data.")
                }
            }
        }
    }
}


/*@Composable
actual fun ComposeMapView(
    vehicleId:Int,
    modifier: Modifier
) {
    // Create a `GMSMapView` instance and configure it
    val googleMapView = remember {
        GMSMapView().apply {
//            setMyLocationEnabled(true)
            *//*settings.setMyLocationButton(true)
            settings.setScrollGestures(true)
            settings.setZoomGestures(true)
            settings.setCompassButton(false)*//*
            // Optionally, set map style
           *//* this.setMapStyle(
                GMSMapStyle.styleWithJSONString(mapStyle1(), error = null)
            )*//*
        }
    }

    // Launch effect to update the map based on location
*//*    LaunchedEffect(locationProvider) {
        val location = locationProvider.getCurrentLocation()
        location?.let {
            googleMapView.animateWithCameraUpdate(
                GMSCameraUpdate.setTarget(
                    CLLocationCoordinate2DMake(it.latitude, it.longitude)
                )
            )
        }
    }*//*

    // Use UIKitView to integrate GMSMapView into Compose
    UIKitView(
        factory = { googleMapView },
        modifier = modifier
    )
}*/
