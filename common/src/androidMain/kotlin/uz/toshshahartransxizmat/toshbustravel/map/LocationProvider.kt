package uz.toshshahartransxizmat.toshbustravel.map


import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

actual open class LocationProvider(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    actual open suspend fun getCurrentLocation(): Location? {
        val lastLocation = fusedLocationClient.lastLocation.await()
        return lastLocation?.let {
            Location(it.latitude, it.longitude)
        }
    }
}


