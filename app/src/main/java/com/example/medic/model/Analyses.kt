package com.example.medic.model

import com.google.gson.annotations.SerializedName

data class Analyses(
	val price: String,
	@SerializedName("time_result")
	val timeResult: String,
	val name: String,
	val description: String,
	val bio: String,
	val id: Int,
	val category: String,
	val preparation: String,
)
