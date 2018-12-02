package com.example.musicsample.data.searched

import com.example.musicsample.data.Song
import com.google.gson.annotations.SerializedName

class SearchedSong() : Song() {

    @SerializedName("trackId")
    private var trackId: Int = 0

    @SerializedName("trackName")
    private var trackName: String? = ""

    @SerializedName("artistName")
    private var artistName: String? = ""

    @SerializedName("artworkUrl100")
    private var artworkUrl: String? = ""

    @SerializedName("previewUrl")
    private lateinit var preview: String

    override val id: Int?
        get() = trackId

    override val title: String?
        get() = trackName

    override val artist: String?
        get() = artistName

    override val coverUrl: String?
        get() = artworkUrl

    override val previewUrl: String?
        get() = preview
}