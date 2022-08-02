package com.markoid.forecast.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.ui.navigation.WeatherScreen
import com.markoid.forecast.ui.widgets.WeatherAppBar
import com.markoid.forecast.viewmodels.FavoriteScreenViewModel

@Composable
fun FavoritesScreen(
  navController: NavController,
  favoriteViewModel: FavoriteScreenViewModel = hiltViewModel()
) {
  Scaffold(
    topBar = {
      WeatherAppBar(
        navController = navController,
        title = "Favorite Cities",
        isMainScreen = false,
        onNavigationClicked = { navController.popBackStack() }
      )
    }
  ) {
    Surface(
      modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
    ) {
      Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        val favoritesList = favoriteViewModel.favoriteList.collectAsState().value
        LazyColumn {
          items(items = favoritesList) {
            CityRow(it, navController, favoriteViewModel)
          }
        }
      }
    }
  }
}

@Composable fun CityRow(
  favoriteEntity: FavoriteEntity,
  navController: NavController,
  favoriteViewModel: FavoriteScreenViewModel
) {
  Surface(
    modifier = Modifier
      .padding(3.dp)
      .fillMaxWidth()
      .height(50.dp)
      .clickable { navController.navigate(WeatherScreen.MainScreen.name + "/${favoriteEntity.city}") },
    shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
    color = Color(0xFFB2DFDB)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      Text(
        text = favoriteEntity.city,
        modifier = Modifier.padding(start = 4.dp)
      )
      Surface(
        modifier = Modifier.padding(0.dp),
        shape = CircleShape,
        color = Color(0xFFD1E3E1)
      ) {
        Text(
          text = favoriteEntity.country,
          modifier = Modifier.padding(4.dp),
          style = MaterialTheme.typography.caption
        )
      }
      Icon(
        imageVector = Rounded.Delete,
        contentDescription = "Delete Icon",
        modifier = Modifier.clickable { favoriteViewModel.deleteFavorite(favoriteEntity) },
        tint = Color.Red.copy(alpha = 0.3f)
      )
    }
  }
}
