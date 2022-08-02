package com.markoid.forecast.model

import com.google.gson.annotations.SerializedName

data class Weather(

  @SerializedName("sunrise")
  private val _sunrise: Long? = null,

  @SerializedName("temp")
  private val _temp: Temp? = null,

  @SerializedName("deg")
  val deg: Int? = null,

  @SerializedName("pressure")
  private val _pressure: Int? = null,

  @SerializedName("clouds")
  val clouds: Int? = null,

  @SerializedName("feels_like")
  val feelsLike: FeelsLike? = null,

  @SerializedName("speed")
  private val _speed: Double? = null,

  @SerializedName("dt")
  private val _dt: Long? = null,

  @SerializedName("pop")
  val pop: Double? = null,

  @SerializedName("sunset")
  private val _sunset: Long? = null,

  @SerializedName("weather")
  private val _weatherItemList: List<WeatherItem?>? = null,

  @SerializedName("humidity")
  private val _humidity: Int? = null,

  @SerializedName("gust")
  val gust: Double? = null
) {

  val milliseconds: Long
    get() = _dt ?: 0L

  val temperature: Temp
    get() = _temp ?: Temp()

  val weatherItem: WeatherItem
    get() = _weatherItemList?.firstOrNull() ?: WeatherItem()

  val humidity: Int
    get() = _humidity ?: -1

  val pressure: Int
    get() = _pressure ?: -1

  val sunrise: Long
    get() = _sunrise ?: 0L

  val sunset: Long
    get() = _sunset ?: 0L

  val speed: Double
    get() = _speed ?: 0.0
}