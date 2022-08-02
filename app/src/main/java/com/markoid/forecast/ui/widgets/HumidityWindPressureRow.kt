package com.markoid.forecast.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.markoid.forecast.R.drawable
import com.markoid.forecast.model.Weather
import com.markoid.forecast.utils.formatDecimals

@Composable
fun HumidityWindPressureRow(
  weather: Weather,
  isImperial: Boolean
) {
  Row(
    modifier = Modifier
      .padding(12.dp)
      .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    ImageLabel(
      text = "${weather.humidity}%",
      image = drawable.humidity,
      contentDescription = "Humidity Icon",
      modifier = Modifier.size(20.dp)
    )
    ImageLabel(
      text = "${weather.pressure} psi",
      image = drawable.pressure,
      contentDescription = "Pressure Icon",
      modifier = Modifier.size(20.dp)
    )
    ImageLabel(
      text = formatDecimals(weather.speed)+ " " + if (isImperial) "mph" else "m/s",
      image = drawable.wind,
      contentDescription = "Pressure Icon",
      modifier = Modifier.size(20.dp)
    )
  }
}