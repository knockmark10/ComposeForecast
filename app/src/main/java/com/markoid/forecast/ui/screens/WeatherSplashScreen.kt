package com.markoid.forecast.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.markoid.forecast.R
import com.markoid.forecast.ui.navigation.DEFAULT_CITY
import com.markoid.forecast.ui.navigation.WeatherScreen
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun WeatherSplashScreen(
  navController: NavController = NavController(LocalContext.current)
) {
  // Value to scale the splash logo
  val scale = remember { Animatable(0f) }

  // Animating the splash logo from 0% to 90% of its size
  LaunchedEffect(key1 = true) {
    scale.animateTo(
      targetValue = 0.9f,
      animationSpec = tween(
        durationMillis = 800,
        easing = { OvershootInterpolator(4f).getInterpolation(it) }
      )
    )
    delay(2000L)
    navController.navigate(route = WeatherScreen.MainScreen.name + "/$DEFAULT_CITY")
  }

  // This is the circle represented by Surface
  Surface(
    modifier = Modifier
      .padding(15.dp)
      .size(330.dp)
      .scale(scale.value),
    shape = CircleShape,
    color = Color.White,
    border = BorderStroke(width = 2.dp, color = Color.LightGray)
  ) {
    Column(
      modifier = Modifier.padding(1.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.sun),
        contentDescription = "Humidity",
        modifier = Modifier.size(95.dp),
        contentScale = ContentScale.Fit
      )
      Text(
        text = "Forecast App",
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Medium
      )
    }
  }
}