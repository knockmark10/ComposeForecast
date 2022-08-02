package com.markoid.forecast.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoid.forecast.model.MeasurementUnit
import com.markoid.forecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel
@Inject constructor(
  private val weatherRepository: WeatherRepository
) : ViewModel() {

  private val _measurementUnitsList = MutableStateFlow<List<MeasurementUnit>>(emptyList())

  val measurementUnitsList: StateFlow<List<MeasurementUnit>>
    get() = _measurementUnitsList.asStateFlow()

  init {
    getMeasurementUnits()
  }

  private fun getMeasurementUnits() = viewModelScope.launch(Dispatchers.IO) {
    weatherRepository.getMeasurementUnits()
      .distinctUntilChanged()
      .collect {
        if (it.isEmpty()) {
          Log.d("TAG", "getFavoriteList: Empty list")
        } else {
          Log.d("TAG", "getFavoriteList: ${it.joinToString(",")}")
          _measurementUnitsList.value = it
        }
      }
  }

  fun insertMeasurementUnits(measurementUnit: MeasurementUnit) =
    viewModelScope.launch(Dispatchers.IO) {
      weatherRepository.insertMeasurementUnit(measurementUnit)
    }

  fun updateMeasurementUnits(measurementUnit: MeasurementUnit) =
    viewModelScope.launch(Dispatchers.IO) {
      weatherRepository.updateMeasurementUnit(measurementUnit)
    }

  fun deleteMeasurementUnits(measurementUnit: MeasurementUnit) =
    viewModelScope.launch(Dispatchers.IO) {
      weatherRepository.deleteMeasurementUnit(measurementUnit)
    }

  fun deleteAllMeasurementUnits() = viewModelScope.launch(Dispatchers.IO) {
    weatherRepository.deleteAllMeasurementUnits()
  }
}