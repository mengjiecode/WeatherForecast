package com.mengjie.weatherforecast.ui

import androidx.lifecycle.*
import com.mengjie.weatherforecast.service.WeatherApiService
import com.mengjie.weatherforecast.data.WeatherData
import com.mengjie.weatherforecast.data.WeatherItem
import com.mengjie.weatherforecast.repository.WeatherRepository
import com.mengjie.weatherforecast.utils.WeatherUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherForecastViewModel : ViewModel() {
    private var _location = MutableLiveData("")
    private var _temperature = MutableLiveData("")
    private var _description = MutableLiveData("")
    private var _iconUrl = MutableLiveData("")
    private var _items = MutableLiveData<List<WeatherItem>>()
    private var _errorMessage = MutableLiveData("")

    val location: LiveData<String> = _location
    val temperature: LiveData<String> = _temperature
    val description: LiveData<String> = _description
    val iconUrl: LiveData<String> = _iconUrl
    val items: LiveData<List<WeatherItem>> = _items
    val errorMessage: LiveData<String> = _errorMessage

    private val repo: WeatherRepository =
        WeatherRepository(WeatherApiService.weatherApi)

    fun displayForecastWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val forecastWeather = repo.getForecastWeather()
                val currentWeather: WeatherData? = forecastWeather.list?.get(0)
                _location.postValue(currentWeather?.name)
                _temperature.postValue("${currentWeather?.main?.temp}°")
                _description.postValue(currentWeather?.weather?.get(0)?.description)
                _iconUrl.postValue(
                    "http://openweathermap.org/img/wn/${currentWeather?.weather?.get(0)?.icon}@2x.png"
                )
                val weatherItems =
                    forecastWeather.list?.filterIndexed { index, _ -> index != 0 }
                        ?.map { weatherData ->
                            WeatherItem(
                                "${WeatherUtils.getDateString(weatherData.dt_txt ?: "")}",
                                "${weatherData.main?.temp}°",
                                "${weatherData.weather?.get(0)?.description}",
                                "http://openweathermap.org/img/wn/${weatherData.weather?.get(0)?.icon}@2x.png"
                            )
                        }
                _items.postValue(weatherItems)
            } catch (e: Exception) {
                _errorMessage.postValue(e.localizedMessage)
            }
        }
    }
}