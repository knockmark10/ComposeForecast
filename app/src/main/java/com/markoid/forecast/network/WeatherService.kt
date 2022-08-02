package com.markoid.forecast.network

import com.markoid.forecast.model.WeatherResponse
import com.markoid.forecast.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

  @GET("data/2.5/forecast/daily")
  suspend fun getWeather(
    @Query("q") query: String,
    @Query("units") units: String = "metric",
    @Query("appid") appId: String = Constants.API_KEY
  ): Response<WeatherResponse>
}