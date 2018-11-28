package com.example.musicsample.repository

import com.example.musicsample.utilis.NetworkCallback
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Locale

class ItuneRepository(okHttpClientClient: OkHttpClient, gson: Gson) :
    StreamingPlatformRepository(okHttpClientClient, gson) {
    override fun getSearch(search: String, callback: NetworkCallback) {
        val url =
            HttpUrl.parse(
                "https://itunes.apple.com/search?term=${search.replace(
                    ' ',
                    '+'
                )}&country=$countryISO&entity=song"
            )
                ?.newBuilder()
                ?.build().toString()

        okHttpClient.newCall(
            Request.Builder()
                .url(url)
                .build()
        ).enqueue(callback)
    }

    private val countryISO = Locale.getDefault().country.toLowerCase()

    override fun getTopHundredHotSongs(callback: NetworkCallback) {
        val url =
            HttpUrl.parse("https://itunes.apple.com/$countryISO/rss/topsongs/limit=100/json")
                ?.newBuilder()
                ?.build().toString()

        okHttpClient.newCall(
            Request.Builder()
                .url(url)
                .build()
        ).enqueue(callback)
    }
}