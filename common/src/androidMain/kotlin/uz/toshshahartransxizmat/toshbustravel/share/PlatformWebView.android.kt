package uz.toshshahartransxizmat.toshbustravel.share

import android.content.Context
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun createPlatformWebView(): PlatformWebView {

    return object : PlatformWebView {

        @Composable
        override fun loadUrl(url: String) {
            val context: Context = LocalContext.current

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(factory = {
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        loadUrl(url)
                    }
                })
            }
        }
    }
}