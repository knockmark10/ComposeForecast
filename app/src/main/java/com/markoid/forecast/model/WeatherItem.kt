package com.markoid.forecast.model

import com.google.gson.annotations.SerializedName

data class WeatherItem(

  @SerializedName("icon")
  private val _icon: String? = null,

  @SerializedName("description")
  private val _description: String? = null,

  @SerializedName("main")
  private val _main: String? = null,

  @SerializedName("id")
  val id: Int? = null
) {

  val icon: String
    get() = _icon ?: ""

  val weatherTitle: String
    get() = _main ?: ""

  val description: String
    get() = _description ?: ""
}