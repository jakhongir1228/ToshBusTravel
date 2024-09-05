package uz.toshshahartransxizmat.toshbustravel.android

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import uz.toshshahartransxizmat.toshbustravel.di.initKoin
import uz.toshshahartransxizmat.toshbustravel.share.appContext
import uz.toshshahartransxizmat.toshbustravel.share.initializeAndroidDeviceIdProvider

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        initKoin {
            androidContext(applicationContext)
        }
        initializeAndroidDeviceIdProvider(this)
    }
}