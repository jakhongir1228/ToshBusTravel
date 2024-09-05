package uz.toshshahartransxizmat.toshbustravel

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIViewController
import uz.toshshahartransxizmat.toshbustravel.map.LocationProvider

@Composable
fun MainViewController(locationProvider: LocationProvider): UIViewController {
    return ComposeUIViewController {
        val isDark = UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark

        Application(
            isDarkTheme = isDark,
            locationProvider = locationProvider
        )
    }
}
/*fun MainViewController(locationProvider: LocationProvider) = ComposeUIViewController {
    val isDark =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark

    Application(
        isDarkTheme = isDark,
        locationProvider = locationProvider

    )
}*/
