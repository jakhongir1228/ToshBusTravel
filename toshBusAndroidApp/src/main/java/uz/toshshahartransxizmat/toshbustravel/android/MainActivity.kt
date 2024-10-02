package uz.toshshahartransxizmat.toshbustravel.android

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.content.ContextCompat
import uz.toshshahartransxizmat.toshbustravel.Application
import uz.toshshahartransxizmat.toshbustravel.map.LocationProvider

class MainActivity : ComponentActivity() {
 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val locationProvider = LocationProvider(context = this)

        setContent {
            Application(isDarkTheme = isSystemInDarkTheme(),
                locationProvider = locationProvider
            )
        }
    }*/

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
            if (fineLocationGranted || coarseLocationGranted) {
                // Permissions are granted, proceed with location access
                initLocationProvider()
            } else {
                // Permissions are not granted, handle the case
                Toast.makeText(this, "Location permissions are required.", Toast.LENGTH_SHORT).show()
            }
        }

        checkAndRequestPermissions()
    }

    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request permissions
            requestPermissionLauncher.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        } else {
            // Permissions are already granted
            initLocationProvider()
        }
    }

    private fun initLocationProvider() {
        val locationProvider = LocationProvider(this)
        setContent {
            Application(isDarkTheme = isSystemInDarkTheme(),
                locationProvider = locationProvider
            )
        }
    }
}
