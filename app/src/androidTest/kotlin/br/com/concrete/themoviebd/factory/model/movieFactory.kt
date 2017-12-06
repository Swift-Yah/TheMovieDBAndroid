package br.com.concrete.themoviebd.factory.model

import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.sdk.model.domain.Movie
import java.util.*

fun randomMovie(): Movie {
    val id = Random().nextInt()
    return Movie(id = id,
            posterPath = "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
            backdropPath = "/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg",
            adult = false,
            video = false,
            releaseDate = "2016-07-27",
            overview = "",
            title = "$id",
            originalTitle = "Jason Bourne",
            originalLanguage = "en",
            genreIds = emptyList(),
            popularity = 30.69,
            voteCount = 649,
            voteAverage = 5.25
    )
}

fun movieList(): List<Movie> {
    return arrayListOf(randomMovie())
}

fun moviePage(): Page<Movie> {
    return pageWith(movieList())
}