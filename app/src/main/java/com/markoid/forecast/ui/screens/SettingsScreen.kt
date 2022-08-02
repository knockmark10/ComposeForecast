package com.markoid.forecast.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.forecast.model.MeasurementUnit
import com.markoid.forecast.ui.widgets.WeatherAppBar
import com.markoid.forecast.viewmodels.SettingsScreenViewModel

@Composable
fun SettingsScreen(
  navController: NavController,
  settingsViewModel: SettingsScreenViewModel = hiltViewModel()
) {
  var unitToggleState by remember { mutableStateOf(false) }
  val measurementUnits = listOf("Imperial (F)", "Metric (C)")
  val choiceFromDb = settingsViewModel.measurementUnitsList.collectAsState().value
  val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnits[0] else choiceFromDb[0].unit
  var choiceState by remember { mutableStateOf(defaultChoice) }

  Scaffold(
    topBar = {
      WeatherAppBar(
        navController = navController,
        title = "Settings",
        isMainScreen = false
      )
    }
  ) {
    Surface(modifier = Modifier.fillMaxSize()) {
      Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "Change units of measurement",
          modifier = Modifier.padding(15.dp)
        )

        IconToggleButton(
          checked = unitToggleState.not(),
          onCheckedChange = {
            unitToggleState = it.not()
            choiceState = if (unitToggleState) "Imperial (F)" else "Metric (C)"
          },
          modifier = Modifier
            .fillMaxWidth(0.5f)
            .clip(shape = RectangleShape)
            .padding(5.dp)
            .background(Color.Magenta)
            .alpha(0.4f)
        ) {
          Text(text = if (unitToggleState) "Fahrenheit ºF" else "Celsius ºC")
        }

        Button(
          modifier = Modifier.padding(3.dp),
          shape = RoundedCornerShape(34.dp),
          colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEFBE42)),
          onClick = {
            settingsViewModel.deleteAllMeasurementUnits()
            settingsViewModel.insertMeasurementUnits(MeasurementUnit(choiceState))
          }
        ) {
          Text(
            text = "Save",
            modifier = Modifier.padding(4.dp),
            fontSize = 17.sp,
            color = Color.White
          )
        }
      }
    }
  }
}