package br.com.concrete.themoviebd.feature.movie

import br.com.concrete.sdk.MovieRepository
import br.com.concrete.sdk.extension.mapPage
import br.com.concrete.themoviebd.base.BaseViewModel
import br.com.concrete.themoviebd.model.mediaItemFrom

class MovieSectionViewModel : BaseViewModel() {

    val nowPlayingLiveData = MovieRepository.getNowPlaying().mapPage(::mediaItemFrom)
    val popularLiveData = MovieRepository.getPopular().mapPage(::mediaItemFrom)
    val topRatedLiveData = MovieRepository.getTopRated().mapPage(::mediaItemFrom)
    val upcomingLiveData = MovieRepository.getUpcoming().mapPage(::mediaItemFrom)

}