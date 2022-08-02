package com.markoid.forecast.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.markoid.forecast.ui.theme.CircleYellow

@Composable
fun WeatherDescription(description: String) {
  Surface(
    modifier = Modifier.padding(1.dp),
    color = CircleYellow,
    shape = CircleShape
  ) {
    Text(
      text = description,
      modifier = Modifier.padding(4.dp),
      style = MaterialTheme.typography.caption
    )
  }
}