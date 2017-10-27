package br.com.concrete.sdk

import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.data.remote.apiInstance
import br.com.concrete.sdk.model.Movie
import br.com.concrete.sdk.model.Page

object MovieRepository {

    fun getMovieDetail(id: Int): ResponseLiveData<Movie> {
        return apiInstance.getMovieDetail(id)
    }

    fun getLatestMovie(): ResponseLiveData<Movie> {
        return apiInstance.getLatestMovie()
    }

    fun getNowPlaying(): ResponseLiveData<Page<Movie>> {
        return apiInstance.getNowPlaying(1)
    }

    fun getPopular(): ResponseLiveData<Page<Movie>> {
        return apiInstance.getPopular(1)
    }

    fun getTopRated(): ResponseLiveData<Page<Movie>> {
        return apiInstance.getTopRated(1)
    }

    fun getUpcoming(): ResponseLiveData<Page<Movie>> {
        return apiInstance.getUpcoming(1)
    }

}