package com.markoid.forecast.datasource

import com.markoid.forecast.dao.WeatherDao
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.model.MeasurementUnit
import com.markoid.forecast.model.WeatherResponse
import com.markoid.forecast.network.WeatherService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDataSourceImpl
@Inject constructor(
  private val weatherService: WeatherService,
  private val weatherDao: WeatherDao
) : WeatherDataSource {

  override suspend fun getWeather(cityQuery: String, unit: String): WeatherResponse {
    val response = weatherService.getWeather(cityQuery, unit)
    if (response.isSuccessful) {
      return response.body() ?: throw IllegalStateException("Empty response body")
    } else {
      throw IllegalStateException("Response was not successful")
    }
  }

  override fun getFavorites(): Flow<List<FavoriteEntity>> = weatherDao.getFavorites()

  override suspend fun getFavoriteByCity(city: String): FavoriteEntity =
    weatherDao.getFavByCity(city)

  override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
    weatherDao.insertFavorite(favoriteEntity)
  }

  override suspend fun updateFavorite(favorite: FavoriteEntity) {
    weatherDao.updateFavorite(favorite)
  }

  override suspend fun deleteAllFavorites() {
    weatherDao.deleteAllFavorites()
  }

  override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
    weatherDao.delete(favoriteEntity)
  }

  override fun getMeasurementUnits(): Flow<List<MeasurementUnit>> = weatherDao.getMeasurementUnits()

  override suspend fun insertMeasurementUnit(measurementUnit: MeasurementUnit) {
    weatherDao.insertMeasurementUnit(measurementUnit)
  }

  override suspend fun updateMeasurementUnit(measurementUnit: MeasurementUnit) {
    weatherDao.updateMeasurementUnit(measurementUnit)
  }

  override suspend fun deleteAllMeasurementUnits() {
    weatherDao.deleteAllMeasurementUnits()
  }

  override suspend fun deleteMeasurementUnit(measurementUnit: MeasurementUnit) {
    weatherDao.delete(measurementUnit)
  }
}