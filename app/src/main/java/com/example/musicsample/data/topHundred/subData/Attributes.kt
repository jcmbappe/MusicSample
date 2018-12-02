package com.example.musicsample.data.topHundred.subData

import com.google.gson.annotations.SerializedName

open class Attributes {
    @SerializedName("im:id")
    var id: Int = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("href")
    var href: String? = null
}