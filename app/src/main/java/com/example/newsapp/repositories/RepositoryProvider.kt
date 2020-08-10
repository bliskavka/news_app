package com.example.newsapp.repositories

object RepositoryProvider {
    fun getNewsRepository(): NewsRepository = NewsRepositoryImpl()
}