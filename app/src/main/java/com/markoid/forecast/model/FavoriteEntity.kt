package com.markoid.forecast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  val id: Long = 0L,

  @ColumnInfo(name = "city")
  val city: String,

  @ColumnInfo(name = "country")
  val country: String

)
