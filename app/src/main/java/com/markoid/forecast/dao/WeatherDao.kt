package com.markoid.forecast.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.model.MeasurementUnit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

  @Query("SELECT * FROM favorite_table")
  fun getFavorites(): Flow<List<FavoriteEntity>>

  @Query("SELECT * FROM favorite_table WHERE city = :city")
  suspend fun getFavByCity(city: String): FavoriteEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun updateFavorite(favorite: FavoriteEntity)

  @Query("DELETE FROM favorite_table")
  suspend fun deleteAllFavorites()

  @Delete
  suspend fun delete(favoriteEntity: FavoriteEntity)

  @Query("SELECT * FROM settings_table")
  fun getMeasurementUnits(): Flow<List<MeasurementUnit>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMeasurementUnit(measurementUnit: MeasurementUnit)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun updateMeasurementUnit(measurementUnit: MeasurementUnit)

  @Query("DELETE FROM settings_table")
  suspend fun deleteAllMeasurementUnits()

  @Delete
  suspend fun delete(measurementUnit: MeasurementUnit)
}