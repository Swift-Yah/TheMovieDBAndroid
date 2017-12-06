package br.com.concrete.themoviebd.sdk.model.domain

import android.os.Parcel
import br.com.concrete.themoviebd.sdk.extension.KParcelable
import br.com.concrete.themoviebd.sdk.extension.parcelableCreator
import com.google.gson.annotations.Expose

data class ImageConfig(
        @Expose val baseUrl: String,
        @Expose val secureBaseUrl: String,
        @Expose val backdropSizes: List<String>,
        @Expose val logoSizes: List<String>,
        @Expose val posterSizes: List<String>,
        @Expose val profileSizes: List<String>,
        @Expose val stillSizes: List<String>
) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::ImageConfig)
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList())

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(baseUrl)
        writeString(secureBaseUrl)
        writeStringList(backdropSizes)
        writeStringList(logoSizes)
        writeStringList(posterSizes)
        writeStringList(profileSizes)
        writeStringList(stillSizes)
    }
}