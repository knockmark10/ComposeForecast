package com.markoid.forecast.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.ui.navigation.WeatherScreen
import com.markoid.forecast.ui.theme.Purple500
import com.markoid.forecast.viewmodels.FavoriteScreenViewModel

@Composable
fun WeatherAppBar(
  navController: NavController,
  title: String = "",
  icon: ImageVector = Icons.Default.ArrowBack,
  isMainScreen: Boolean = true,
  elevation: Dp = 0.dp,
  viewModel: FavoriteScreenViewModel = hiltViewModel(),
  onSearchButtonClicked: () -> Unit = {},
  onNavigationClicked: () -> Unit = { navController.popBackStack() }
) {
  val showDialog = remember { mutableStateOf(false) }
  val showToast = remember { mutableStateOf(false) }

  if (showDialog.value) {
    SettingsDropDrownMenu(showDialog = showDialog, navController = navController)
  }

  val navigationIcon: @Composable (() -> Unit)? = if (isMainScreen.not()) {
    {
      IconButton(onClick = onNavigationClicked) {
        Icon(
          imageVector = icon,
          contentDescription = "Back Icon",
          tint = Color.White,
          modifier = Modifier.clickable(onClick = onNavigationClicked)
        )
      }
    }
  } else null

  TopAppBar(
    title = {
      Text(
        text = title,
        color = Color.White
      )
    },
    actions = {
      if (isMainScreen) {
        IconButton(
          onClick = {
            val titleSplitData = title.split(",")
            val favoriteCity = FavoriteEntity(
              city = titleSplitData[0],
              country = titleSplitData[1].trim()
            )
            viewModel.insertFavorite(favoriteCity)
          }
        ) {

          // Check if the city title is present in the list from db
          val isCitySavedAsFavorite = viewModel.favoriteList
            .collectAsState()
            .value
            .any { it.city == title.split(",")[0] }

          // Update show toast value with the boolean from above
          showToast.value = isCitySavedAsFavorite

          // Choose filled heart icon or an empty one depending on whether it's saved or not
          val favoriteIcon = if (isCitySavedAsFavorite) {
            Icons.Default.Favorite
          } else {
            Icons.Default.FavoriteBorder
          }

          // Display favorite icon
          Icon(
            imageVector = favoriteIcon,
            contentDescription = "Favorite Icon",
            tint = Color.Red.copy(alpha = 0.75f)
          )
        }
        IconButton(onClick = onSearchButtonClicked) {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = Color.White
          )
        }
        IconButton(onClick = { showDialog.value = !showDialog.value }) {
          Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "More Icon",
            tint = Color.White
          )
        }
      } else Box {}

      AppToast(showIt = showToast)
    },
    navigationIcon = navigationIcon,
    backgroundColor = Purple500,
    elevation = elevation
  )
}

@Composable
fun SettingsDropDrownMenu(
  showDialog: MutableState<Boolean>,
  navController: NavController
) {
  val items = listOf("About", "Favorites", "Settings")
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentSize(align = Alignment.TopEnd)
      .absolutePadding(top = 45.dp, right = 20.dp)
  ) {
    DropdownMenu(
      expanded = showDialog.value,
      onDismissRequest = { showDialog.value = false },
      modifier = Modifier
        .width(140.dp)
        .background(Color.White)
    ) {
      items.forEachIndexed { index, item ->

        // Getting the route from the clicked item
        val route = when (item) {
          "About" -> WeatherScreen.AboutScreen.name
          "Favorites" -> WeatherScreen.FavoriteScreen.name
          else -> WeatherScreen.SettingsScreen.name
        }

        DropdownMenuItem(
          onClick = {
            navController.navigate(route = route)
            showDialog.value = false
          }
        ) {
          Icon(
            imageVector = when (item) {
              "About" -> Icons.Default.Info
              "Favorites" -> Icons.Default.FavoriteBorder
              else -> Icons.Default.Settings
            },
            contentDescription = null,
            tint = Color.LightGray
          )

          // Small margin after the icon
          Spacer(modifier = Modifier.width(8.dp))

          // Setting the text for the item
          Text(
            text = item,
            fontWeight = FontWeight.W300
          )
        }
      }
    }
  }
}
