package com.example.newsapp.repositories

import androidx.lifecycle.LiveData
import com.example.newsapp.entities.News

interface NewsRepository {
    fun getAllNews(): LiveData<List<News>>
    fun getPageOfNews(page: Int): LiveData<List<News>>
}