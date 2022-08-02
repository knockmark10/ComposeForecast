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
import com.markoid.forecast.utils.formatDateTime

@Composable
fun SunsetSunriseRow(weather: Weather) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 15.dp, bottom = 6.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    ImageLabel(
      text = formatDateTime(weather.sunrise),
      image = drawable.sunrise,
      contentDescription = "Sunrise Icon",
      modifier = Modifier.size(30.dp)
    )
    ImageLabel(
      text = formatDateTime(weather.sunset),
      image = drawable.sunset,
      contentDescription = "Sunset Icon",
      modifier = Modifier.size(30.dp),
      iconAtStart = false
    )
  }
}