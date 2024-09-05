package uz.toshshahartransxizmat.toshbustravel.share

import android.content.Context
import android.provider.Settings

class AndroidDeviceIdProvider(private val context: Context) : DeviceIdProvider {
    override fun getDeviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}

// Application yoki istalgan Activityda contextni o'rnating
fun initializeAndroidDeviceIdProvider(context: Context) {
    deviceIdProvider = AndroidDeviceIdProvider(context)
}