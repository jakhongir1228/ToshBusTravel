package uz.toshshahartransxizmat.toshbustravel.share

import androidx.compose.runtime.Composable
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView

@Composable
actual fun createPlatformWebView(): PlatformWebView {

    return object : PlatformWebView {

        @Composable
        override fun loadUrl(url: String) {
            val webView = WKWebView()
            val request = NSURLRequest(NSURL(string = url))
            webView.loadRequest(request)
        }
    }
}