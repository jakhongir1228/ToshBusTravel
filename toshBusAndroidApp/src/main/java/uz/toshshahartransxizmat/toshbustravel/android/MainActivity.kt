package uz.toshshahartransxizmat.toshbustravel.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import uz.toshshahartransxizmat.toshbustravel.Application

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Application(isDarkTheme = isSystemInDarkTheme())
        }
    }
}
