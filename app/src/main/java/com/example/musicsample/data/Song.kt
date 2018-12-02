package com.example.musicsample.data

import android.os.Parcelable

abstract class Song : Parcelable {
    abstract val id: Int?
    abstract val title: String?
    abstract val artist: String?
    abstract val coverUrl: String?
    abstract val previewUrl: String?
}