package br.com.concrete.themoviebd.sdk.model.domain

import android.os.Parcel
import br.com.concrete.themoviebd.sdk.extension.KParcelable
import br.com.concrete.themoviebd.sdk.extension.parcelableCreator
import br.com.concrete.themoviebd.sdk.extension.readBoolean
import br.com.concrete.themoviebd.sdk.extension.writeBoolean
import com.google.gson.annotations.Expose

data class Movie(
        @Expose val id: Int,
        @Expose val posterPath: String?,
        @Expose val backdropPath: String?,
        @Expose val adult: Boolean,
        @Expose val video: Boolean,
        @Expose val releaseDate: String,
        @Expose val overview: String,
        @Expose val title: String,
        @Expose val originalTitle: String,
        @Expose val originalLanguage: String,
        @Expose val genreIds: List<Int>,
        @Expose val popularity: Double,
        @Expose val voteCount: Int,
        @Expose val voteAverage: Double
) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::Movie)
    }

    private constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readBoolean(),
            parcel.readBoolean(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createIntArray().asList(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble())

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(posterPath)
        writeString(backdropPath)
        writeBoolean(adult)
        writeBoolean(video)
        writeString(releaseDate)
        writeString(overview)
        writeString(title)
        writeString(originalTitle)
        writeString(originalLanguage)
        writeIntArray(genreIds.toIntArray())
        writeDouble(popularity)
        writeInt(voteCount)
        writeDouble(voteAverage)
    }
}