package uz.toshshahartransxizmat.toshbustravel.share

import androidx.compose.runtime.Composable

interface PlatformWebView {
    @Composable
    fun loadUrl(url: String)
}

@Composable
expect fun createPlatformWebView(): PlatformWebView
