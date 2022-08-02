package com.markoid.forecast.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.markoid.forecast.model.Weather

@Composable
fun WeatherWeekList(
  listTitle: String,
  items: List<Weather>
) {
  Text(
    text = listTitle,
    style = MaterialTheme.typography.subtitle1,
    fontWeight = FontWeight.Bold
  )
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = Color(0xFFEEF1EF),
    shape = RoundedCornerShape(size = 14.dp)
  ) {
    LazyColumn(
      modifier = Modifier.padding(2.dp),
      contentPadding = PaddingValues(1.dp)
    ) {
      items(items = items) { weatherItem ->
        WeatherDetailRow(weather = weatherItem)
      }
    }
  }
}