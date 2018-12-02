package com.example.musicsample.data.topHundred

import com.google.gson.annotations.SerializedName

open class TopHundredResult {

    @SerializedName("feed")
    lateinit var feed: Feed

    open class Feed {
        @SerializedName("entry")
        lateinit var entries: ArrayList<TopHundredSong>
    }
}