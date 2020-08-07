package com.example.newsapp.webapi

import com.example.newsapp.entities.News
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApi {
    @GET
    fun getAllNews(@Url url: String): Call<List<News>>
    @GET
    fun getPageOfNews(@Url url: String, count: Int): Call<List<News>>
    @GET
    fun getImage(@Url url: String): Call<ResponseBody>
}