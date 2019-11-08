package com.mengjie.weatherforecast

import androidx.multidex.MultiDexApplication
import com.mengjie.weatherforecast.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class WeatherApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setup()
    }

    private fun setup() {
        if (GlobalContext.getOrNull() == null) {
            val viewModelModule = module {
                viewModel {
                    MainViewModel()
                }
            }

            startKoin {
                androidContext(this@WeatherApplication)
                modules(viewModelModule)
            }
        }
    }
}