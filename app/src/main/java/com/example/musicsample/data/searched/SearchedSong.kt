package com.example.musicsample.data.searched

import android.os.Parcel
import android.os.Parcelable
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
    private  var preview: String? = ""

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

    constructor(parcel: Parcel) : this() {
        trackId = parcel.readInt()
        trackName = parcel.readString()
        artistName = parcel.readString()
        artworkUrl = parcel.readString()
        preview = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(trackId)
        parcel.writeString(trackName)
        parcel.writeString(artistName)
        parcel.writeString(artworkUrl)
        parcel.writeString(preview)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchedSong> {
        override fun createFromParcel(parcel: Parcel): SearchedSong {
            return SearchedSong(parcel)
        }

        override fun newArray(size: Int): Array<SearchedSong?> {
            return arrayOfNulls(size)
        }
    }
}