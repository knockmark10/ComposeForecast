package com.markoid.forecast.utils

object IconBuilder {

  fun getImageUrlForId(id: String): String =
    "${Constants.WEATHER_ICON_URL}$id.png"
}