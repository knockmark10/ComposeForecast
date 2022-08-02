package com.markoid.forecast.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageLabel(
  text: String,
  @DrawableRes image: Int,
  contentDescription: String,
  modifier: Modifier = Modifier,
  iconAtStart: Boolean = true
) {
  Row(modifier = Modifier.padding(4.dp)) {
    if (iconAtStart) {
      Icon(
        painter = painterResource(id = image),
        contentDescription = contentDescription,
        modifier = modifier
      )
      Text(
        text = text,
        style = MaterialTheme.typography.caption
      )
    } else {
      Text(
        text = text,
        style = MaterialTheme.typography.caption
      )
      Icon(
        painter = painterResource(id = image),
        contentDescription = contentDescription,
        modifier = modifier
      )
    }
  }
}