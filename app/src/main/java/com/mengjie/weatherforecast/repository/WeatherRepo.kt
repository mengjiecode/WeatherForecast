package com.mengjie.weatherforecast.repository


import com.mengjie.weatherforecast.service.WeatherService
import com.mengjie.weatherforecast.data.WeatherDataList

class WeatherRepo(private val weatherService: WeatherService) {

    suspend fun getForecastWeather(): WeatherDataList {
        return weatherService.getForecastWeather()
    }

}