package br.com.concrete.themoviebd.sdk

import br.com.concrete.themoviebd.sdk.data.ResponseLiveData
import br.com.concrete.themoviebd.sdk.data.remote.apiInstance
import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.sdk.model.domain.TvShow

object TVShowRepository {

    fun getAiringToday(): ResponseLiveData<Page<TvShow>> {
        return apiInstance.tvShowAiringToday(1)
    }

    fun getPopular(): ResponseLiveData<Page<TvShow>> {
        return apiInstance.tvShowPopular(1)
    }

    fun getTopRated(): ResponseLiveData<Page<TvShow>> {
        return apiInstance.tvShowTopRated(1)
    }

    fun getOnTheAir(): ResponseLiveData<Page<TvShow>> {
        return apiInstance.tvShowOnTheAir(1)
    }

}