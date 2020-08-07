package com.example.newsapp.entities

import android.graphics.Bitmap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(@SerializedName("title") val title: String,
                @SerializedName("type") val type: Type,
                @SerializedName("click_url") val clickUrl: String,
                @SerializedName("time") val time: String,
                @SerializedName("top") val top: Int,
                @SerializedName("img") var imgUrl: String = "",
                var img: Bitmap? = null){


}

enum class Type(val title: String){
    //TODO add strings to resources
    FAVOURITES ("Favourites"), VIDEO ("Video"), STORIES("Stories")
}