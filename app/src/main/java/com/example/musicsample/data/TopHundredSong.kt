package com.example.musicsample.data

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

    override val id: Int?
        get() = inId.attributes.id

    override val title: String?
        get() = imName.label

    override val artist: String?
        get() = inArtist.label

    override val coverUrl: String?
        get() = inCoverList.lastOrNull()?.label

    open class Id {
        @SerializedName("attributes")
        lateinit var attributes: Attributes

        open class Attributes {
            @SerializedName("im:id")
            var id: Int = 0
        }
    }

    open class Label {
        @SerializedName("label")
        lateinit var label: String
    }
}