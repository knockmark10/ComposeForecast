package com.markoid.forecast.model

import com.google.gson.annotations.SerializedName

data class FeelsLike(

	@SerializedName("eve")
	val eve: Double? = null,

	@SerializedName("night")
	val night: Double? = null,

	@SerializedName("day")
	val day: Double? = null,

	@SerializedName("morn")
	val morn: Double? = null
)