package uz.toshshahartransxizmat.toshbustravel.ui.apply

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.map.Location


internal class ApplyForScreen : Screen {

    @Composable
    override fun Content() {
//        val coroutineScope = rememberCoroutineScope()
//        val context = LocalContext.current
//
//        var location by remember { mutableStateOf<Location?>(null) }
//
//        val locationProvider = remember { LocationProviderImpl(context) } // Context'ni argumentda berish
//
//        LaunchedEffect(Unit) {
//            coroutineScope.launch {
//                location = locationProvider.getCurrentLocation()
//            }
//        }
        Text("  Kartani shu Screenda chiqarishimiz kerak.")

    }
}