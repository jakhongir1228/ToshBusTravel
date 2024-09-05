package uz.toshshahartransxizmat.toshbustravel.share

import android.content.Context
import android.content.SharedPreferences

class AndroidSettingsSource(private val sharedPreferences: SharedPreferences) : SettingsSource {
    override fun saveValue(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getValue(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}

lateinit var appContext: Context

actual fun getSettingsSource(): SettingsSource {
    val sharedPreferences = appContext.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    return AndroidSettingsSource(sharedPreferences)
}