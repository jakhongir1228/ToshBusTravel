package uz.toshshahartransxizmat.toshbustravel.util

import androidx.compose.runtime.Composable

object LanguageManager {
    private var currentLanguage: String = "ru"

    fun getCurrentLanguage(): String = currentLanguage

    fun setLanguage(language: String) {
        currentLanguage = language
    }
}

@Composable
fun getStrings(resourceKey: String): String {
    val language = LanguageManager.getCurrentLanguage()
    return when (language) {
        "uz" -> uzStrings[resourceKey] ?: resourceKey
        "ru" -> ruStrings[resourceKey] ?: resourceKey
        else -> enStrings[resourceKey] ?: resourceKey
    }
}
