package br.com.concrete.themoviebd.model

import android.os.Parcel
import br.com.concrete.themoviebd.sdk.extension.KParcelable
import br.com.concrete.themoviebd.sdk.extension.parcelableCreator
import br.com.concrete.themoviebd.sdk.model.domain.Movie
import br.com.concrete.themoviebd.sdk.model.domain.TvShow

data class MediaItem(val imagePath: String, val title: String) : KParcelable {

    private constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(imagePath)
        writeString(title)
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::MediaItem)

    }
}

fun mediaItemFrom(movie: Movie) = MediaItem(movie.posterPath!!, movie.title)
fun mediaItemFrom(tvShow: TvShow) = MediaItem(tvShow.posterPath!!, tvShow.name)