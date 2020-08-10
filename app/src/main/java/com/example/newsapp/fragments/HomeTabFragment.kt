package com.example.newsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.R
import com.example.newsapp.adapters.HomeTabsViewPagerAdapter
import com.example.newsapp.entities.News
import com.example.newsapp.entities.Type
import com.example.newsapp.repositories.NewsRepository
import com.example.newsapp.repositories.RepositoryProvider
import com.example.newsapp.viewmodels.MainActivityViewModel
import com.example.newsapp.viewmodels.NewsCallback

private const val ARG_TYPE = "position"

class HomeTabFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    private val TAG = "HomeTabFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Passing requireActivity() as lifecycle owner, so not to initialise new viewModel for each fragment
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)

        arguments?.takeIf { it.containsKey(ARG_TYPE) }?.apply {
            when (getSerializable(ARG_TYPE)) {
                Type.STORIES -> setUpForStories()
                Type.VIDEO -> setUpForVideo()
                Type.FAVOURITES -> setUpForFavourites()
            }
        }
    }
    private fun setUpForStories() {
        viewModel.setStoriesRetrieveListener(object : NewsCallback.Stories{
            override fun onRetrieved(news: List<News>) {
            }
        })

    }

    private fun setUpForVideo() {
        viewModel.setVideoRetrieveListener(object : NewsCallback.Video{
            override fun onRetrieved(news: List<News>) {
            }
        })
    }

    private fun setUpForFavourites() {
        viewModel.setFavouritesRetrieveListener(object : NewsCallback.Favourites{
            override fun onRetrieved(news: List<News>) {
            }
        })
    }


}