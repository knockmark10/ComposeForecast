package com.markoid.forecast.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.markoid.forecast.dao.WeatherDao
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.model.MeasurementUnit

@Database(
  entities = [FavoriteEntity::class, MeasurementUnit::class],
  version = 2,
  exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

  abstract fun weatherDao(): WeatherDao

}