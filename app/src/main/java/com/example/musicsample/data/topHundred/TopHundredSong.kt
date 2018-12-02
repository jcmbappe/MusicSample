package com.example.musicsample.data.topHundred

import android.os.Parcel
import android.os.Parcelable
import com.example.musicsample.data.Song
import com.example.musicsample.data.topHundred.subData.Id
import com.example.musicsample.data.topHundred.subData.Label
import com.example.musicsample.data.topHundred.subData.Link
import com.google.gson.annotations.SerializedName

open class TopHundredSong() : Song() {

    @SerializedName("id")
    private lateinit var inId: Id

    @SerializedName("im:name")
    private lateinit var imName: Label

    @SerializedName("im:artist")
    private lateinit var inArtist: Label

    @SerializedName("im:image")
    private lateinit var inCoverList: List<Label>

    @SerializedName("link")
    private lateinit var links: List<Link>

    override val id: Int?
        get() = inId.attributes?.id

    override val title: String?
        get() = imName.label

    override val artist: String?
        get() = inArtist.label

    override val coverUrl: String?
        get() = inCoverList.lastOrNull()?.label

    override val previewUrl: String?
        get() = links.lastOrNull()?.attributes?.href

    constructor(parcel: Parcel) : this() {
        inId = parcel.readParcelable(Id::class.java.classLoader)
        imName = parcel.readParcelable(Label::class.java.classLoader)
        inArtist = parcel.readParcelable(Label::class.java.classLoader)
        inCoverList = parcel.createTypedArrayList(Label)
        links = parcel.createTypedArrayList(Link)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(inId, flags)
        parcel.writeParcelable(imName, flags)
        parcel.writeParcelable(inArtist, flags)
        parcel.writeTypedList(inCoverList)
        parcel.writeTypedList(links)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopHundredSong> {
        override fun createFromParcel(parcel: Parcel): TopHundredSong {
            return TopHundredSong(parcel)
        }

        override fun newArray(size: Int): Array<TopHundredSong?> {
            return arrayOfNulls(size)
        }
    }
}