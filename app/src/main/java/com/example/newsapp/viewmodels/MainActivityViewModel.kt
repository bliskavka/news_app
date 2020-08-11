package com.example.newsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.entities.News
import com.example.newsapp.entities.Type
import com.example.newsapp.repositories.RepositoryProvider

class MainActivityViewModel : ViewModel() {
    private val repository = RepositoryProvider.getNewsRepository()

    private var storiesCallback: NewsCallback.Stories? = null
    private var videoCallback: NewsCallback.Video? = null
    private var favouritesCallback: NewsCallback.Favourites? = null

    private val storiesList = ArrayList<News>()
    private val videoList = ArrayList<News>()
    private val favouritesList = ArrayList<News>()

    init {
        //TODO clear observer
        repository.getAllNews()
            .observeForever {

                for (item in it) {
                    if (item.type == (Type.STORIES))
                        storiesList.indexOf(item).let {if (it == -1) storiesList.add(item) else storiesList.set(it, item)}

                    if (item.type == (Type.VIDEO))
                        videoList.indexOf(item).let {if (it == -1) videoList.add(item) else videoList.set(it, item)}

                    if (item.type == (Type.FAVOURITES))
                        favouritesList.indexOf(item).let {if (it == -1) favouritesList.add(item) else favouritesList.set(it, item)}
                }
                storiesCallback?.onRetrieved(storiesList)
                videoCallback?.onRetrieved(videoList)
                favouritesCallback?.onRetrieved(favouritesList)
            }
    }

    fun getAllNews(): LiveData<List<News>> {
        return repository.getAllNews()
    }

    /**
     *  Setting up callbacks for different types of news and calling it on initial dataList
     *  so client could get updates even if listener was tied up after
     *  repository callback happened in init() method
     */
    fun setStoriesRetrieveListener(callback: NewsCallback.Stories) {
        storiesCallback = callback
        storiesCallback?.onRetrieved(storiesList)
    }

    fun setVideoRetrieveListener(callback: NewsCallback.Video) {
        videoCallback = callback
        videoCallback?.onRetrieved(videoList)
    }

    fun setFavouritesRetrieveListener(callback: NewsCallback.Favourites) {
        favouritesCallback = callback
        favouritesCallback?.onRetrieved(favouritesList)
    }

}