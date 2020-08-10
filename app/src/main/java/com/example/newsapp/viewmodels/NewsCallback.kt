package com.example.newsapp.viewmodels

import androidx.lifecycle.LiveData
import com.example.newsapp.entities.News

interface NewsCallback {
    interface Stories{
        fun onRetrieved(news: List<News>)
    }
    interface Video{
        fun onRetrieved(news: List<News>)
    }
    interface Favourites{
        fun onRetrieved(news: List<News>)
    }
}