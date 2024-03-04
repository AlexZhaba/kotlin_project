package com.example.kotlin_project.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

class Product(
    val id: Long,
    val title: String,
    @DrawableRes
     val image: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(this.id)
        parcel.writeString(this.title)
        parcel.writeInt(this.image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}