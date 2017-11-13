package br.com.concrete.themoviebd.transform

import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.extension.map
import br.com.concrete.sdk.extension.mapData
import br.com.concrete.sdk.extension.poster
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.domain.Movie
import br.com.concrete.sdk.model.type.MEDIUM
import br.com.concrete.themoviebd.model.MediaItem

fun ResponseLiveData<Page<Movie>>.toMediaItemPage() = mapData { page ->
    page.map { movie ->
        MediaItem(movie.poster(MEDIUM), movie.title)
    }
}