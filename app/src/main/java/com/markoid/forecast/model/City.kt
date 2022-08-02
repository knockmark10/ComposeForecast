package com.markoid.forecast.model

import com.google.gson.annotations.SerializedName

data class City(

  @SerializedName("country")
  val country: String? = null,

  @SerializedName("coord")
  val coord: Coord? = null,

  @SerializedName("timezone")
  val timezone: Int? = null,

  @SerializedName("name")
  private val _name: String? = null,

  @SerializedName("id")
  val id: Int? = null,

  @SerializedName("population")
  val population: Int? = null
) {

  val name: String
    get() = _name ?: ""
}