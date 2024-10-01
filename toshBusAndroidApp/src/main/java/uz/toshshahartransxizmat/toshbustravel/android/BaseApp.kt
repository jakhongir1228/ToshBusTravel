package uz.toshshahartransxizmat.toshbustravel.android

import android.app.Application
import android.content.Context
import com.google.android.libraries.places.api.Places
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
        Places.initialize(applicationContext, "AIzaSyD7i6d8teRDVvWJ3SdeATBrG74liSChL5I")

    }
}