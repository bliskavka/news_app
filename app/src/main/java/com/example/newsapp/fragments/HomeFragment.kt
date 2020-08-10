package com.example.newsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.adapters.HomeTabsViewPagerAdapter
import com.example.newsapp.entities.Type
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.fragment_homepage.view.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.news_title)

        viewPager.adapter = HomeTabsViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            when (position){
                0 -> tab.text = Type.STORIES.toString()
                1 -> tab.text = Type.VIDEO.toString()
                2 -> tab.text = Type.FAVOURITES.toString()
            }
        }.attach()
    }
}