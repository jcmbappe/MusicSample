package com.example.musicsample.data.topHundred.subData

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class Attributes() : Parcelable {
    @SerializedName("im:id")
    var id: Int = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("href")
    var href: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        href = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(href)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Attributes> {
        override fun createFromParcel(parcel: Parcel): Attributes {
            return Attributes(parcel)
        }

        override fun newArray(size: Int): Array<Attributes?> {
            return arrayOfNulls(size)
        }
    }
}