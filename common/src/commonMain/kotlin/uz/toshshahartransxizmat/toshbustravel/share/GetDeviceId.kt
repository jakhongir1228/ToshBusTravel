package uz.toshshahartransxizmat.toshbustravel.share

interface DeviceIdProvider {
    fun getDeviceId(): String
}

lateinit var deviceIdProvider: DeviceIdProvider

fun provideDeviceId(): String = deviceIdProvider.getDeviceId()