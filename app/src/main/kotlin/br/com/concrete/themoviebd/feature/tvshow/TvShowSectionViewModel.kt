package br.com.concrete.themoviebd.feature.tvshow

import br.com.concrete.themoviebd.base.BaseViewModel
import br.com.concrete.themoviebd.model.mediaItemFrom
import br.com.concrete.themoviebd.sdk.TVShowRepository
import br.com.concrete.themoviebd.sdk.extension.mapPage

class TvShowSectionViewModel : BaseViewModel() {

    val onTheAirLiveData = TVShowRepository.getOnTheAir().mapPage(::mediaItemFrom)
    val popularLiveData = TVShowRepository.getPopular().mapPage(::mediaItemFrom)
    val topRatedLiveData = TVShowRepository.getTopRated().mapPage(::mediaItemFrom)
    val airingTodayLiveData = TVShowRepository.getAiringToday().mapPage(::mediaItemFrom)

}