package com.example.shoppingapp.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

data class itemShopping(
    var _id: String,
    var title : String,
    var desc : String,
    var image : String,
    var price:Int,
    var inStock:Boolean,
    var inCart:Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    @SuppressLint("NewApi")
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(title)
        parcel.writeString(desc)
        parcel.writeString(image)
        parcel.writeInt(price)
        parcel.writeBoolean(inStock)
        parcel.writeBoolean(inCart)
    }

    companion object CREATOR : Parcelable.Creator<itemShopping> {
        override fun createFromParcel(parcel: Parcel): itemShopping {
            return itemShopping(parcel)
        }

        override fun newArray(size: Int): Array<itemShopping?> {
            return arrayOfNulls(size)
        }
    }
}
