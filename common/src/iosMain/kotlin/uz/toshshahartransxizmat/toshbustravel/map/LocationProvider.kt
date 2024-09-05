package uz.toshshahartransxizmat.toshbustravel.map

import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationCoordinate2D
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import uz.toshshahartransxizmat.toshbustravel.map.Location

actual class LocationProvider {
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
}