package com.markoid.forecast.ui.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppToast(
  context: Context = LocalContext.current,
  showIt: MutableState<Boolean> = mutableStateOf(false)
) {
  if (showIt.value) {
    Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
  }
}