package com.markoid.forecast.model

import com.google.gson.annotations.SerializedName

data class Temp(

  @SerializedName("min")
  private val _min: Double? = null,

  @SerializedName("max")
  private val _max: Double? = null,

  @SerializedName("eve")
  val eve: Double? = null,

  @SerializedName("night")
  val night: Double? = null,

  @SerializedName("day")
  private val _day: Double? = null,

  @SerializedName("morn")
  val morn: Double? = null
) {

  val day: Double
    get() = _day ?: 0.0

  val max: Double
    get() = _max ?: 0.0

  val min: Double
    get() = _min ?: 0.0
}
