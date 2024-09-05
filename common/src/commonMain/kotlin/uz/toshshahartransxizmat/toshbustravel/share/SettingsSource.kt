package uz.toshshahartransxizmat.toshbustravel.share

interface SettingsSource {
    fun saveValue(key: String, value: String)
    fun getValue(key: String): String?
}

expect fun getSettingsSource(): SettingsSource