package uz.toshshahartransxizmat.toshbustravel

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import uz.toshshahartransxizmat.toshbustravel.map.LocationProvider

fun MainViewController() = ComposeUIViewController {
    val isDark =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark

    val locationProvider = LocationProvider()

    Application(
        isDarkTheme = isDark,
        locationProvider = locationProvider

    )
}


