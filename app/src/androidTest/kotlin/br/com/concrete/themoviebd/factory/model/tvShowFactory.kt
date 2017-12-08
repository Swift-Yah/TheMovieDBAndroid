package br.com.concrete.themoviebd.factory.model

import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.sdk.model.domain.Movie
import br.com.concrete.themoviebd.sdk.model.domain.TvShow
import java.util.*

fun randomTvShow(): TvShow {
    val id = Random().nextInt()
    return TvShow(id = id,
            posterPath = "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
            backdropPath = "/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg",
            firstAirDate = "2016-07-27",
            overview = "",
            name = "$id",
            originalName = "Jason Bourne",
            originalLanguage = "en",
            genreIds = emptyList(),
            originCountry = emptyList(),
            popularity = 30.69,
            voteCount = 649,
            voteAverage = 5.25
    )
}

fun tvShowList(): List<TvShow> {
    return arrayListOf(randomTvShow())
}

fun tvShowPage(): Page<TvShow> {
    return pageWith(tvShowList())
}