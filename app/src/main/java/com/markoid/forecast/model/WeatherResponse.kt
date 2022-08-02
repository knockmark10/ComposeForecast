package com.markoid.forecast.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

  @SerializedName("city")
  private val _city: City? = null,

  @SerializedName("cnt")
  private val _cnt: Int? = null,

  @SerializedName("list")
  private val _list: List<Weather>? = null
) {

  val city: City
    get() = _city ?: City()

  val count: Int
    get() = _cnt ?: 0

  val weather: Weather
    get() = _list?.firstOrNull() ?: Weather()

  val weatherList: List<Weather>
    get() = _list ?: emptyList()
}