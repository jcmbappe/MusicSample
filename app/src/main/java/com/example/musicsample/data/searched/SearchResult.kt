package com.example.musicsample.data.searched

import com.google.gson.annotations.SerializedName

open class SearchResult {

    @SerializedName("results")
    lateinit var results: ArrayList<SearchedSong>
}