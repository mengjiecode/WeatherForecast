package com.mengjie.weatherforecast

import androidx.multidex.MultiDexApplication
import com.mengjie.weatherforecast.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setup()
    }

    private fun setup() {
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(weatherModule)
        }
    }
}