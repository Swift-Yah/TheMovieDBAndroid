package br.com.concrete.themoviebd.sdk

import br.com.concrete.themoviebd.sdk.data.ResponseLiveData
import br.com.concrete.themoviebd.sdk.data.remote.apiInstance
import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.sdk.model.domain.Movie

object MovieRepository {

    fun getMovieDetail(id: Int): ResponseLiveData<Movie> {
        return apiInstance.movieDetail(id)
    }

    fun getLatestMovie(): ResponseLiveData<Movie> {
        return apiInstance.latestMovie()
    }

    fun getNowPlaying(): ResponseLiveData<Page<Movie>> {
        return apiInstance.movieNowPlaying(1)
    }

    fun getPopular(): ResponseLiveData<Page<Movie>> {
        return apiInstance.moviePopular(1)
    }

    fun getTopRated(): ResponseLiveData<Page<Movie>> {
        return apiInstance.movieTopRated(1)
    }

    fun getUpcoming(): ResponseLiveData<Page<Movie>> {
        return apiInstance.movieUpcoming(1)
    }

}