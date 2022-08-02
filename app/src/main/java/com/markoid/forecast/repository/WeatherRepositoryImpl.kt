package com.markoid.forecast.repository

import android.util.Log
import com.markoid.forecast.data.DataOrException
import com.markoid.forecast.datasource.WeatherDataSource
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.model.MeasurementUnit
import com.markoid.forecast.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
  private val weatherDataSource: WeatherDataSource
) : WeatherRepository {

  override suspend fun getWeather(cityQuery: String, unit: String): DataOrException<WeatherResponse> {
    Log.d("leissue", "Unit $unit")
    val response = try {
      weatherDataSource.getWeather(cityQuery, unit)
    } catch (e: Throwable) {
      return DataOrException(error = e)
    }

    return DataOrException(response)
  }

  override fun getFavorites(): Flow<List<FavoriteEntity>> = weatherDataSource.getFavorites()

  override suspend fun getFavoriteByCity(city: String): FavoriteEntity =
    weatherDataSource.getFavoriteByCity(city)

  override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
    weatherDataSource.insertFavorite(favoriteEntity)
  }

  override suspend fun updateFavorite(favorite: FavoriteEntity) {
    weatherDataSource.updateFavorite(favorite)
  }

  override suspend fun deleteAllFavorites() {
    weatherDataSource.deleteAllFavorites()
  }

  override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
    weatherDataSource.deleteFavorite(favoriteEntity)
  }

  override suspend fun deleteMeasurementUnit(measurementUnit: MeasurementUnit) {
    weatherDataSource.deleteMeasurementUnit(measurementUnit)
  }

  override fun getMeasurementUnits(): Flow<List<MeasurementUnit>> =
    weatherDataSource.getMeasurementUnits()

  override suspend fun insertMeasurementUnit(measurementUnit: MeasurementUnit) {
    weatherDataSource.insertMeasurementUnit(measurementUnit)
  }

  override suspend fun updateMeasurementUnit(measurementUnit: MeasurementUnit) {
    weatherDataSource.updateMeasurementUnit(measurementUnit)
  }

  override suspend fun deleteAllMeasurementUnits() {
    weatherDataSource.deleteAllMeasurementUnits()
  }
}