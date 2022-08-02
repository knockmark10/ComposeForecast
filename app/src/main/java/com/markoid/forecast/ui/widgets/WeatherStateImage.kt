package com.markoid.forecast.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun WeatherStateImage(imageUrl: String) {
  Image(
    painter = rememberImagePainter(data = imageUrl),
    contentDescription = "Icon image",
    modifier = Modifier.size(80.dp)
  )
}