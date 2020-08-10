package com.example.newsapp.webapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val baseUrl = "http://188.40.167.45:3001"
    private var retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsApi(): NewsApi{
        return retrofit.create(NewsApi::class.java)
    }
}