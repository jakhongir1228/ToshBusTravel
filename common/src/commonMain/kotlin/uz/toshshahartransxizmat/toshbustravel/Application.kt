package uz.toshshahartransxizmat.toshbustravel


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import uz.toshshahartransxizmat.toshbustravel.map.LocationProvider
import uz.toshshahartransxizmat.toshbustravel.theme.ToshBusTravelTheme
import uz.toshshahartransxizmat.toshbustravel.ui.apply.ApplyForScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.AuthScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.ForgotPasswordScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.LogInScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.language.LanguageScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.logo.LogoScreen
import uz.toshshahartransxizmat.toshbustravel.ui.home.HomeScreen
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.TransportDetailsScreen

@Composable
fun Application(
    isDarkTheme:Boolean,
    locationProvider: LocationProvider
){

    ToshBusTravelTheme(
        isDarkTheme = isDarkTheme
    ){
        Scaffold {
            var showScreen by remember { mutableStateOf(true) }

            if (showScreen) {
                LogoScreen(onNavigate = {
                    showScreen = false
                })
            } else {
                Navigator(
                    LanguageScreen()
                  //  LogInScreen()
                  //  AuthScreen()
                  //  ForgotPasswordScreen()
                  //  OtpConfirmationScreen()
                   //   HomeScreen()
                   //  TransportDetailsScreen(vehicleId = 1)
                   // ApplyForScreen(locationProvider)
                )
            }
        }
    }
}
