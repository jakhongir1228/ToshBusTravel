package uz.toshshahartransxizmat.toshbustravel

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import uz.toshshahartransxizmat.toshbustravel.theme.ToshBusTravelTheme
import uz.toshshahartransxizmat.toshbustravel.ui.auth.language.LanguageScreen
import uz.toshshahartransxizmat.toshbustravel.ui.auth.logo.LogoScreen

@Composable
fun Application(
    isDarkTheme:Boolean
){
    ToshBusTravelTheme(
        isDarkTheme = isDarkTheme
    ){
//        TabNavigator(HomeTab) {
//            Scaffold (
//                bottomBar = {
//                    NavigationBar {
//                        BottomItem(HomeTab)
//                        BottomItem(OrdersTab)
//                        BottomItem(ProfileTab)
//                    }
//                }
//            ){
//                CurrentTab()
//            }
//        }

        Scaffold {
            var showScreen by remember { mutableStateOf(true) }

            if (showScreen) {
                LogoScreen(onNavigate = {
                    showScreen = false
                })
            } else {
                LanguageScreen()
            }
        }
    }
}
