package br.com.concrete.themoviebd.sdk.model.domain

import android.os.Parcel
import br.com.concrete.themoviebd.sdk.extension.KParcelable
import br.com.concrete.themoviebd.sdk.extension.parcelableCreator
import com.google.gson.annotations.Expose

data class TvShow(
        @Expose val id: Int,
        @Expose val posterPath: String?,
        @Expose val backdropPath: String?,
        @Expose val firstAirDate: String,
        @Expose val overview: String,
        @Expose val name: String,
        @Expose val originalName: String,
        @Expose val originalLanguage: String,
        @Expose val genreIds: List<Int>,
        @Expose val originCountry: List<String>,
        @Expose val popularity: Double,
        @Expose val voteCount: Int,
        @Expose val voteAverage: Double
) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::TvShow)
    }

    private constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createIntArray().asList(),
            parcel.createStringArrayList(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble())

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(posterPath)
        writeString(backdropPath)
        writeString(firstAirDate)
        writeString(overview)
        writeString(name)
        writeString(originalName)
        writeString(originalLanguage)
        writeIntArray(genreIds.toIntArray())
        writeStringList(originCountry)
        writeDouble(popularity)
        writeInt(voteCount)
        writeDouble(voteAverage)
    }
}