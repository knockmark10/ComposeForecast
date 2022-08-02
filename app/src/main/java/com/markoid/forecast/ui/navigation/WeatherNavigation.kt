package com.markoid.forecast.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.markoid.forecast.ui.screens.AboutScreen
import com.markoid.forecast.ui.screens.FavoritesScreen
import com.markoid.forecast.ui.screens.MainScreen
import com.markoid.forecast.ui.screens.SearchScreen
import com.markoid.forecast.ui.screens.SettingsScreen
import com.markoid.forecast.ui.screens.WeatherSplashScreen
import com.markoid.forecast.viewmodels.MainScreenViewModel

const val DEFAULT_CITY = "Zapopan"
const val CITY = "city"

@Composable
fun WeatherNavigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = WeatherScreen.SplashScreen.name
  ) {

    // Splash node
    composable(
      route = WeatherScreen.SplashScreen.name
    ) {
      WeatherSplashScreen(navController)
    }

    // Main Screen node
    val route = WeatherScreen.MainScreen.name
    composable(
      route = "$route/{$CITY}",
      arguments = listOf(
        navArgument(name = CITY) {
          type = NavType.StringType
          defaultValue = ""
        }
      )
    ) {
      val city = it.arguments.getCity(DEFAULT_CITY)
      val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
      MainScreen(
        city = city,
        navController = navController,
        mainViewModel = mainScreenViewModel
      )
    }

    // Search Screen node
    composable(route = WeatherScreen.SearchScreen.name) {
      SearchScreen(navController = navController)
    }

    // About Screen node
    composable(route = WeatherScreen.AboutScreen.name) {
      AboutScreen(navController = navController)
    }

    // Favorite Screen node
    composable(route = WeatherScreen.FavoriteScreen.name) {
      FavoritesScreen(navController = navController)
    }

    // Settings Screen node
    composable(route = WeatherScreen.SettingsScreen.name) {
      SettingsScreen(navController = navController)
    }
  }
}

fun Bundle?.getCity(defaultValue: String = ""): String {
  val args = this ?: Bundle()
  return args.getString(CITY) ?: defaultValue
}