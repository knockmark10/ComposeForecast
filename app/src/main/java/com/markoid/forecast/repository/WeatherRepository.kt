package com.markoid.forecast.repository

import com.markoid.forecast.data.DataOrException
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.model.MeasurementUnit
import com.markoid.forecast.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
  suspend fun getWeather(cityQuery: String, unit: String): DataOrException<WeatherResponse>
  fun getFavorites(): Flow<List<FavoriteEntity>>
  suspend fun getFavoriteByCity(city: String): FavoriteEntity
  suspend fun insertFavorite(favoriteEntity: FavoriteEntity)
  suspend fun updateFavorite(favorite: FavoriteEntity)
  suspend fun deleteAllFavorites()
  suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
  fun getMeasurementUnits(): Flow<List<MeasurementUnit>>
  suspend fun insertMeasurementUnit(measurementUnit: MeasurementUnit)
  suspend fun updateMeasurementUnit(measurementUnit: MeasurementUnit)
  suspend fun deleteAllMeasurementUnits()
  suspend fun deleteMeasurementUnit(measurementUnit: MeasurementUnit)
}