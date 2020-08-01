package com.mengjie.weatherforecast.di

import com.mengjie.weatherforecast.ui.WeatherForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    viewModel { WeatherForecastViewModel() }
}