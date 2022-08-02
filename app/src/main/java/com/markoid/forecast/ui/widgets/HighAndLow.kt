package com.markoid.forecast.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun HighAndLow(
  high: String,
  low: String
) {
  Text(text = buildAnnotatedString {
    withStyle(
      style = SpanStyle(
        color = Color.Blue.copy(alpha = 0.65f),
        fontWeight = FontWeight.SemiBold
      )
    ) {
      append(high + "º")
    }
    withStyle(
      style = SpanStyle(
        color = Color.LightGray,
        fontWeight = FontWeight.SemiBold
      )
    ) {
      append(low + "º")
    }
  })
}