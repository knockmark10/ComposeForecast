package com.markoid.forecast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class MeasurementUnit(

  @PrimaryKey
  @ColumnInfo(name = "unit")
  val unit: String

)
