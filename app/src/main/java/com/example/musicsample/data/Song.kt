package com.example.musicsample.data

abstract class Song {
    abstract val id: Int?
    abstract val title: String?
    abstract val artist: String?
    abstract val coverUrl: String?
}