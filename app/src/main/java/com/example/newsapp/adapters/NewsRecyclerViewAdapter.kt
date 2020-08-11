package com.example.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.entities.News
import kotlinx.android.synthetic.main.news_item.view.*
import org.w3c.dom.Text

class NewsRecyclerViewAdapter(val context: Context?) : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {
    private var dataList = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(h: ViewHolder, position: Int) {
        val item = dataList[position]

        h.title.text = item.title
        h.time.text = "- ${item.time}"
        h.url.text = if(item.clickUrl.isNullOrEmpty()) item.url else item.clickUrl

        //TODO add image loading progressbar if there is no image but imageUrl is notnull
        item.imgBitmap?.let { h.image.setImageBitmap(item.imgBitmap) }
    }

    fun update(data: List<News>){
        dataList = data as ArrayList<News>
        notifyDataSetChanged() //TODO replace with DiffUtil
    }


    class ViewHolder (v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.imageView
        val title: TextView = v.titleTextView
        val time: TextView = v.timeTextView
        val url: TextView = v.urlTextView
    }
}