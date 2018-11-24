package com.example.musicsample.data

import com.google.gson.annotations.SerializedName

open class TopHundred {

    @SerializedName("feed")
    lateinit var feed: Feed

    open class Feed {
        @SerializedName("entry")
        lateinit var entries: ArrayList<Entry>
    }
}