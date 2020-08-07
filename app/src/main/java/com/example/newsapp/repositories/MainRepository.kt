package com.example.newsapp.repositories

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.entities.News
import com.example.newsapp.webapi.NetworkService
import com.example.newsapp.webapi.NewsApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    val TAG = "Repository"
    fun start() {
        NetworkService.getNewsApi()
            .getAllNews(NetworkService.baseUrl)
            .enqueue(object : Callback<List<News>> {
                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    val news = response.body()
                    Log.d(TAG, "onResponse: $news")
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    Log.d(TAG, "Failure!")
                    t.printStackTrace()
                }
            })
    }

    private fun setImages(news: List<News>) {
        for (newsItem in news) {
            NetworkService.getNewsApi()
                .getImage(newsItem.imgUrl)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        val stream = response.body()?.byteStream()
                        val bitmap = BitmapFactory.decodeStream(stream)
                        newsItem.img = bitmap
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

        }
        val newsLiveData = MutableLiveData<List<News>>()
        newsLiveData.postValue(news)
    }
}