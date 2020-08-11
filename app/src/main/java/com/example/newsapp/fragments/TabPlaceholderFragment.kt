package com.example.newsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsRecyclerViewAdapter
import com.example.newsapp.entities.News
import com.example.newsapp.entities.Type
import com.example.newsapp.viewmodels.MainActivityViewModel
import com.example.newsapp.viewmodels.NewsCallback
import kotlinx.android.synthetic.main.fragment_news.*

private const val ARG_TYPE = "position"

class TabPlaceholderFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    private val TAG = "HomeTabFragment"
    private var adapter: NewsRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Passing requireActivity() as lifecycle owner, so not to initialise new viewModel for each fragment
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)

        adapter = NewsRecyclerViewAdapter(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

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
                adapter?.update(news)
            }
        })

    }

    private fun setUpForVideo() {
        viewModel.setVideoRetrieveListener(object : NewsCallback.Video{
            override fun onRetrieved(news: List<News>) {
                adapter?.update(news)
            }
        })
    }

    private fun setUpForFavourites() {
        viewModel.setFavouritesRetrieveListener(object : NewsCallback.Favourites{
            override fun onRetrieved(news: List<News>) {
                adapter?.update(news)
            }
        })
    }


}