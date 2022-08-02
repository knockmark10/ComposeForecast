package com.markoid.forecast.viewmodels

import androidx.lifecycle.ViewModel
import com.markoid.forecast.data.DataOrException
import com.markoid.forecast.model.WeatherResponse
import com.markoid.forecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel
@Inject constructor(
  private val weatherRepository: WeatherRepository
) : ViewModel() {

  suspend fun getWeatherData(city: String, units: String): DataOrException<WeatherResponse> =
    weatherRepository.getWeather(
      cityQuery = city,
      unit = units
    )
}