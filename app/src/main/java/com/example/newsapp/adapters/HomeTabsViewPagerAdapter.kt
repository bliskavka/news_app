package com.example.newsapp.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.entities.Type
import com.example.newsapp.fragments.TabPlaceholderFragment

private const val ARG_TYPE = "position"

class HomeTabsViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter (fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = TabPlaceholderFragment()
        when (position){
            0 -> fragment.arguments = Bundle().apply {
                putSerializable(ARG_TYPE, Type.STORIES)
            }
            1 -> fragment.arguments = Bundle().apply {
                putSerializable(ARG_TYPE, Type.VIDEO)
            }
            2 -> fragment.arguments = Bundle().apply {
                putSerializable(ARG_TYPE, Type.FAVOURITES)
            }
        }
        return fragment
    }
}