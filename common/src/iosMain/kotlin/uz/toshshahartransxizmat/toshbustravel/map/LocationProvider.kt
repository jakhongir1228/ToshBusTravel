package uz.toshshahartransxizmat.toshbustravel.map

import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse

actual open class LocationProvider {
    private val locationManager = CLLocationManager()

    actual suspend fun getCurrentLocation(): Location? {
        if (CLLocationManager.authorizationStatus() == kCLAuthorizationStatusAuthorizedWhenInUse) {
            locationManager.requestLocation()  // Request location update
            val lastLocation = locationManager.location
            return lastLocation?.let {
                it.coordinate.useContents {
                    Location(latitude, longitude)
                }
            }
        }
        return null
    }
/*private val fusedLocationClient: FusedLocationProviderClient =
    LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    actual suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        continuation.resume(
                            Location(location.latitude, location.longitude)
                        )
                    } else {
                        continuation.resume(null)
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }*/
}