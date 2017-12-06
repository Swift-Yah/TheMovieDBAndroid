package br.com.concrete.themoviebd.feature.movie

import br.com.concrete.themoviebd.base.BaseViewModel
import br.com.concrete.themoviebd.model.mediaItemFrom
import br.com.concrete.themoviebd.sdk.MovieRepository
import br.com.concrete.themoviebd.sdk.extension.mapPage

class MovieSectionViewModel : BaseViewModel() {

    val nowPlayingLiveData = MovieRepository.getNowPlaying().mapPage(::mediaItemFrom)
    val popularLiveData = MovieRepository.getPopular().mapPage(::mediaItemFrom)
    val topRatedLiveData = MovieRepository.getTopRated().mapPage(::mediaItemFrom)
    val upcomingLiveData = MovieRepository.getUpcoming().mapPage(::mediaItemFrom)

}