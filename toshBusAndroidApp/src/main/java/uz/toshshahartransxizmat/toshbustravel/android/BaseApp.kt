package uz.toshshahartransxizmat.toshbustravel.android

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import uz.toshshahartransxizmat.toshbustravel.di.initKoin

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}