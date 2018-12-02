package com.example.musicsample.data.topHundred

import com.example.musicsample.data.Song
import com.example.musicsample.data.topHundred.subData.Id
import com.example.musicsample.data.topHundred.subData.Label
import com.example.musicsample.data.topHundred.subData.Link
import com.google.gson.annotations.SerializedName

open class TopHundredSong : Song() {

    @SerializedName("id")
    private lateinit var inId: Id

    @SerializedName("im:name")
    private lateinit var imName: Label

    @SerializedName("im:artist")
    private lateinit var inArtist: Label

    @SerializedName("im:image")
    private lateinit var inCoverList: ArrayList<Label>

    @SerializedName("link")
    private lateinit var links: ArrayList<Link>

    override val id: Int?
        get() = inId.attributes.id

    override val title: String?
        get() = imName.label

    override val artist: String?
        get() = inArtist.label

    override val coverUrl: String?
        get() = inCoverList.lastOrNull()?.label

    override val previewUrl: String?
        get() = links.find { it.attributes.title == "Extrait" }?.attributes?.href
}