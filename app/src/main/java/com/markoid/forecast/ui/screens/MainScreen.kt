package com.markoid.forecast.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.forecast.data.DataOrException
import com.markoid.forecast.model.WeatherResponse
import com.markoid.forecast.ui.navigation.WeatherScreen
import com.markoid.forecast.ui.theme.CircleYellow
import com.markoid.forecast.ui.widgets.HumidityWindPressureRow
import com.markoid.forecast.ui.widgets.SunsetSunriseRow
import com.markoid.forecast.ui.widgets.WeatherAppBar
import com.markoid.forecast.ui.widgets.WeatherStateImage
import com.markoid.forecast.ui.widgets.WeatherWeekList
import com.markoid.forecast.utils.IconBuilder
import com.markoid.forecast.utils.formatDate
import com.markoid.forecast.utils.formatDecimals
import com.markoid.forecast.viewmodels.MainScreenViewModel
import com.markoid.forecast.viewmodels.SettingsScreenViewModel

@Composable
fun MainScreen(
  city: String,
  mainViewModel: MainScreenViewModel = hiltViewModel(),
  navController: NavController,
  settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel()
) {
  val currentCity = city.ifBlank { "Zapopan" }
  val unitFromDb = settingsScreenViewModel.measurementUnitsList.collectAsState().value
  var measurementUnit by remember { mutableStateOf("Imperial") }
  var isImperial by remember { mutableStateOf(false) }

  measurementUnit = if (unitFromDb.isNotEmpty()) {
    unitFromDb[0].unit.split(" ")[0].lowercase()
  } else {
    "imperial"
  }

  isImperial = measurementUnit == "imperial"

  val weatherData = produceState<DataOrException<WeatherResponse>>(
    initialValue = DataOrException(loading = true)
  ) {
    value = mainViewModel.getWeatherData(
      city = currentCity,
      units = measurementUnit
    )
  }.value

  when {
    weatherData.loading == true -> CircularProgressIndicator()
    weatherData.data != null -> MainScaffold(
      weather = weatherData.data!!,
      navController = navController,
      isImperial = isImperial
    )
  }
}

@Composable
fun MainScaffold(
  weather: WeatherResponse,
  navController: NavController,
  isImperial: Boolean
) {
  Scaffold(
    modifier = Modifier,
    topBar = {
      WeatherAppBar(
        title = "${weather.city.name}, ${weather.city.country}",
        navController = navController,
        icon = Icons.Default.ArrowBack,
        onSearchButtonClicked = { navController.navigate(WeatherScreen.SearchScreen.name) }
      ) {
        Log.d("TAG", "MainScaffold: Button Clicked")
      }
    }
  ) {
    MainContent(data = weather, isImperial = isImperial)
  }
}

@Composable
fun MainContent(
  data: WeatherResponse,
  isImperial: Boolean
) {
  Column(
    modifier = Modifier
      .padding(4.dp)
      .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = formatDate(data.weather.milliseconds),
      style = MaterialTheme.typography.caption,
      color = MaterialTheme.colors.onSecondary,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(6.dp)
    )
    Surface(
      modifier = Modifier
        .padding(4.dp)
        .size(200.dp),
      shape = CircleShape,
      color = CircleYellow
    ) {
      Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        WeatherStateImage(imageUrl = IconBuilder.getImageUrlForId(data.weather.weatherItem.icon))
        Text(
          text = formatDecimals(data.weather.temperature.day) + if (isImperial) "º F" else "º C",
          style = MaterialTheme.typography.h4,
          fontWeight = FontWeight.ExtraBold
        )
        Text(
          text = data.weather.weatherItem.weatherTitle,
          fontStyle = FontStyle.Italic,
          fontWeight = Companion.Light
        )
      }
    }
    HumidityWindPressureRow(weather = data.weather, isImperial)
    Divider()
    SunsetSunriseRow(weather = data.weather)
    WeatherWeekList(
      listTitle = "This Week",
      items = data.weatherList
    )
  }
}



