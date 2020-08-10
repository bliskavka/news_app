package com.example.newsapp.repositories

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.entities.News
import com.example.newsapp.webapi.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepositoryImpl : NewsRepository {
    private val TAG = "Repository"

    override fun getAllNews(): LiveData<List<News>> {
        val newsLiveData = MutableLiveData<List<News>>()
        getRemoteNews(newsLiveData, 0, false)
        return newsLiveData
    }

    override fun getPageOfNews(page: Int): LiveData<List<News>> {
        val newsLiveData = MutableLiveData<List<News>>()
        getRemoteNews(newsLiveData, page, true)
        return newsLiveData
    }

    /**
     *  This method takes LiveData object fills it up with data
     *  and than passes this object to setRemoteImages method
     *  so main data could be delivered before pictures are downloaded
     *  @param dataList the LiveData instance for what to pass the data
     *  @param startPage first page to get data from
     *  @param singlePage if true, only one page will be retrieved, otherwise dataList will be
     *          populated until all pages run out
     */
    private fun getRemoteNews(dataList: MutableLiveData<List<News>>, startPage: Int, singlePage: Boolean) {
        RetrofitService.getNewsApi()
            .getPageOfNews(RetrofitService.baseUrl, startPage)
            .enqueue(object : Callback<List<News>> {
                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    val receivedList = response.body()
                    if (receivedList?.size != 0 && receivedList is List<News>) {
                        Log.d(TAG, "Successfully received ${receivedList?.size} items")
                        dataList.value = receivedList
                        setRemoteImages(dataList)

                        // Using recursive method to get data from next page (i.e. calling itself)
                        if (!singlePage){
                            getRemoteNews(dataList, startPage + 1, false)
                        }
                    } else {
                        Log.d(TAG, "No items in response")
                    }
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    Log.d(TAG, "Request Failure!")
                    t.printStackTrace()
                }
            })

    }

    private fun setRemoteImages(dataList: MutableLiveData<List<News>>) {
        dataList.value?.forEach {
            if (it.imgUrl != null) {
                RetrofitService.getNewsApi()
                    .getImage(it.imgUrl)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            val stream = response.body()?.byteStream()
                            val bitmap = BitmapFactory.decodeStream(stream)
                            it.imgBitmap = bitmap
                            dataList.value = dataList.value // force postValue to notify Observers
                            Log.d(TAG, "Image load successful: ${it.imgBitmap}")
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                            Log.d(TAG, "Image Failure!")
                        }
                    })
            }
        }
    }

}