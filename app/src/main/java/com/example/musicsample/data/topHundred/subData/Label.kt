package com.example.musicsample.data.topHundred.subData

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class Label() : Parcelable{
    @SerializedName("label")
    var label: String? = null

    constructor(parcel: Parcel) : this() {
        label = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Label> {
        override fun createFromParcel(parcel: Parcel): Label {
            return Label(parcel)
        }

        override fun newArray(size: Int): Array<Label?> {
            return arrayOfNulls(size)
        }
    }
}