package com.mengjie.weatherforecast.data

data class WeatherDataList(
    val list: List<WeatherData>?,
    val city: City?
)

data class City(
    val name: String?
)

data class WeatherData(
    val name: String?,
    val main: Main?,
    val weather: List<Weather>?,
    val dt_txt: String?
)

data class Main(
    val temp : Double?,
    val temp_min: Double?,
    val temp_max: Double?
)

data class Weather(
    val id: Int?,
    val icon: String?,
    val main: String?,
    val description: String?
)


