package com.markoid.forecast.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.markoid.forecast.model.Weather
import com.markoid.forecast.utils.IconBuilder
import com.markoid.forecast.utils.formatDate
import com.markoid.forecast.utils.formatDecimals

@Composable
fun WeatherDetailRow(weather: Weather) {
  val imageUrl = IconBuilder.getImageUrlForId(weather.weatherItem.icon)
  Surface(
    modifier = Modifier
      .padding(3.dp)
      .fillMaxWidth(),
    shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
    color = Color.White
  ) {
    Row(
      modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        text = formatDate(weather.milliseconds).substring(0, 3),
        modifier = Modifier.padding(start = 5.dp)
      )
      WeatherStateImage(imageUrl = imageUrl)
      WeatherDescription(description = weather.weatherItem.description)
      HighAndLow(
        high = formatDecimals(weather.temperature.max),
        low = formatDecimals(weather.temperature.min)
      )
    }
  }
}