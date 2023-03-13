package com.example.medic.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {
    val retrofit : MedicApi = Retrofit.Builder()
        .baseUrl("https://medic.madskill.ru/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create()
}