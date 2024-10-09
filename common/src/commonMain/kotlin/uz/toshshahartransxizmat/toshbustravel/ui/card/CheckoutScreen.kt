package uz.toshshahartransxizmat.toshbustravel.ui.card

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import uz.toshshahartransxizmat.toshbustravel.share.PlatformWebView
import uz.toshshahartransxizmat.toshbustravel.share.createPlatformWebView

internal class CheckoutScreen: Screen {

    @Composable
    override fun Content() {
        val platformWebView: PlatformWebView = createPlatformWebView()
        val url = "https://www.kapital24.uz/uz/deposit/"
        platformWebView.loadUrl(url)
    }
}
