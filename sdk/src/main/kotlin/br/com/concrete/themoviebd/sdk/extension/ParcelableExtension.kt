@file:JvmName("ParcelableUtils")

package br.com.concrete.themoviebd.sdk.extension

import android.os.Parcel
import android.os.Parcelable

interface KParcelable : Parcelable {
    override fun describeContents() = 0
    override fun writeToParcel(dest: Parcel, flags: Int)
}

// Creator factory functions
inline fun <reified T> parcelableCreator(crossinline create: (Parcel) -> T) =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel) = create(source)
            override fun newArray(size: Int) = arrayOfNulls<T>(size)
        }

// Parcel extensions
internal fun <T : Parcelable> Parcel.readTypedObjectCompat(creator: Parcelable.Creator<T>) =
        readNullable { creator.createFromParcel(this) }

internal fun <T : Parcelable> Parcel.writeTypedObjectCompat(value: T?, flags: Int) =
        writeNullable(value) { it.writeToParcel(this, flags) }

internal fun Parcel.readBoolean() = readByte() != 0.toByte()

internal fun Parcel.writeBoolean(value: Boolean?) = writeByte((if (value == true) 1 else 0).toByte())

internal inline fun <T> Parcel.readNullable(reader: () -> T) = if (readInt() != 0) reader() else null

internal inline fun <T> Parcel.writeNullable(value: T?, writer: (T) -> Unit) {
    if (value == null) writeInt(0)
    else {
        writeInt(1)
        writer(value)
    }
}