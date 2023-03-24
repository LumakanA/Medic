package com.example.medic.data

import com.example.medic.model.Analyses
import com.example.medic.model.News
import retrofit2.http.GET

interface MedicApi {

    @GET("catalog")
    suspend fun getCatalog() : List<Analyses>

    @GET("news")
    suspend fun getNews(): List<News>
}