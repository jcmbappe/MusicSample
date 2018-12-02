package com.example.musicsample.data.topHundred.subData

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class Id() : Parcelable {
    @SerializedName("attributes")
    var attributes: Attributes? = null

    constructor(parcel: Parcel) : this() {
        attributes = parcel.readParcelable(Attributes::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(attributes, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Id> {
        override fun createFromParcel(parcel: Parcel): Id {
            return Id(parcel)
        }

        override fun newArray(size: Int): Array<Id?> {
            return arrayOfNulls(size)
        }
    }
}
