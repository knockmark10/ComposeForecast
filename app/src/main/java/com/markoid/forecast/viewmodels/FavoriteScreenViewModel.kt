package com.markoid.forecast.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoid.forecast.model.FavoriteEntity
import com.markoid.forecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel
@Inject constructor(
  private val weatherRepository: WeatherRepository
) : ViewModel() {

  private val _favoriteList = MutableStateFlow<List<FavoriteEntity>>(emptyList())

  val favoriteList: StateFlow<List<FavoriteEntity>>
    get() = _favoriteList.asStateFlow()

  init {
    getFavoriteList()
  }

  private fun getFavoriteList() = viewModelScope.launch(Dispatchers.IO) {
    weatherRepository.getFavorites()
      .distinctUntilChanged()
      .collect {
        if (it.isEmpty()) {
          Log.d("TAG", "getFavoriteList: Empty list")
        } else {
          Log.d("TAG", "getFavoriteList: ${it.joinToString(",")}")
          _favoriteList.value = it
        }
      }
  }

  fun insertFavorite(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
    weatherRepository.insertFavorite(favorite)
  }

  fun updateFavorite(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
    weatherRepository.updateFavorite(favorite)
  }

  fun deleteFavorite(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
    weatherRepository.deleteFavorite(favorite)
  }
}