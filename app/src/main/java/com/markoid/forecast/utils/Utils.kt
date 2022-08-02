package com.markoid.forecast.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long): String {
  val simpleDateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
  val date = Date(timestamp * 1000L)
  return simpleDateFormat.format(date)
}

fun formatDateTime(timestamp: Long): String {
  val simpleDateFormat = SimpleDateFormat("hh:mm:aa", Locale.getDefault())
  val date = Date(timestamp * 1000L)
  return simpleDateFormat.format(date)
}

fun formatDecimals(item: Double): String = "%.0f".format(item)