package com.example.newsapp.entities

import android.graphics.Bitmap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(@SerializedName("title") val title: String,
                @SerializedName("type") val type: Type,
                @SerializedName("click_url") val clickUrl: String,
                @SerializedName("url") val url: String,
                @SerializedName("time") val time: String,
                @SerializedName("top") val top: Int,
                @SerializedName("img") val imgUrl: String?,
                var imgBitmap: Bitmap? = null){

    override fun equals(other: Any?): Boolean {
        if (other !is News) return false
        if (other.title != title) return false
        if (other.type != type) return false
        if (other.top != top) return false
        if (other.imgUrl != imgUrl) return false
        if (other.clickUrl != clickUrl) return false
        if (other.time != time) return false
        return true
    }
}

enum class Type(){
    //TODO add strings to resources

    @SerializedName("favourites")
    FAVOURITES () {
        override fun toString(): String = "Favourites"
    },

    @SerializedName("video")
    VIDEO {
        override fun toString(): String = "Video"
    },

    @SerializedName("strories")
    STORIES {
        override fun toString(): String = "Stories"
    };
}

