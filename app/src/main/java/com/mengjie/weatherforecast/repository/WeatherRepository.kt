package com.mengjie.weatherforecast.repository


import com.mengjie.weatherforecast.api.WeatherApi
import com.mengjie.weatherforecast.model.WeatherDataList

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getForecastWeather(): WeatherDataList {
        return api.getForecastWeather()
    }

}