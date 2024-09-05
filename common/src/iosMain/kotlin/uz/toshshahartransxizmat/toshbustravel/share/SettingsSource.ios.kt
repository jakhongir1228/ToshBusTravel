package uz.toshshahartransxizmat.toshbustravel.share

import platform.Foundation.NSUserDefaults

class IOSSettingsSource : SettingsSource {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    override fun saveValue(key: String, value: String) {
        userDefaults.setObject(value, forKey = key)
    }

    override fun getValue(key: String): String? {
        return userDefaults.stringForKey(key)
    }
}

actual fun getSettingsSource(): SettingsSource = IOSSettingsSource()