package com.example.musicsample.repository

import com.example.musicsample.utilis.NetworkCallback
import com.google.gson.Gson
import okhttp3.Callback
import okhttp3.OkHttpClient


abstract class StreamingPlatformRepository(val okHttpClient: OkHttpClient, val gson: Gson) {
    abstract fun getTopHundredHotSongs(callback : NetworkCallback)
}