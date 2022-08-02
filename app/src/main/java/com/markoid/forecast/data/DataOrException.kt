package com.markoid.forecast.data

class DataOrException<T>(
  var data: T? = null,
  var loading: Boolean? = null,
  var error: Throwable? = null
)