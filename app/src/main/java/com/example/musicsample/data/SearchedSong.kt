package com.example.musicsample.data

import com.google.gson.annotations.SerializedName

<<<<<<< Updated upstream
class SongSearch : Song() {
=======
class SearchedSong : Song() {
>>>>>>> Stashed changes

    @SerializedName("trackId")
    private var trackId: Int = 0

    @SerializedName("trackName")
    private var trackName: String? = ""

    @SerializedName("artistName")
    private var artistName: String? = ""

    @SerializedName("artworkUrl100")
    private var artworkUrl: String? = ""

    override val id: Int?
        get() = trackId

    override val title: String?
        get() = trackName

    override val artist: String?
        get() = artistName

    override val coverUrl: String?
        get() = artworkUrl
}