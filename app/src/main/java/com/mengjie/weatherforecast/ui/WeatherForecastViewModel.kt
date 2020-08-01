package com.mengjie.weatherforecast.ui

import androidx.lifecycle.*
import com.mengjie.weatherforecast.service.WeatherApiHelper
import com.mengjie.weatherforecast.data.WeatherData
import com.mengjie.weatherforecast.data.WeatherItem
import com.mengjie.weatherforecast.repository.WeatherRepo
import com.mengjie.weatherforecast.utils.WeatherUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


class WeatherForecastViewModel : ViewModel(), KoinComponent {

    private val _location: MutableLiveData<String> = MutableLiveData()
    val location: LiveData<String>
        get() = _location

    private val _temperature: MutableLiveData<String> = MutableLiveData()
    val temperature: LiveData<String>
        get() = _temperature

    private val _description: MutableLiveData<String> = MutableLiveData()
    val description: LiveData<String>
        get() = _description

    private val _iconUrl: MutableLiveData<String> = MutableLiveData()
    val iconUrl: LiveData<String>
        get() = _iconUrl

    private val _weatherItemList: MutableLiveData<List<WeatherItem>> = MutableLiveData()
    val items: LiveData<List<WeatherItem>>
        get() = _weatherItemList

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private fun updateWeatherItemList(items: List<WeatherItem>) {
        _weatherItemList.value = items
    }

    private val repo: WeatherRepo =
        WeatherRepo(WeatherApiHelper.weatherService)

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
                _weatherItemList.postValue(weatherItems)
            } catch (e: Exception) {
                _errorMessage.postValue(e.localizedMessage)
            }
        }
    }
}