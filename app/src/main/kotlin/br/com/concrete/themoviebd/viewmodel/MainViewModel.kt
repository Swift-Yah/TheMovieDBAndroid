package br.com.concrete.themoviebd.viewmodel

import br.com.concrete.sdk.ConfigRepository
import br.com.concrete.sdk.MovieRepository
import br.com.concrete.sdk.extension.addSource
import br.com.concrete.themoviebd.viewmodel.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val configLiveData = ConfigRepository.getConfiguration()

    val latestLiveData = MovieRepository.getLatestMovie()
    val movieDetailLiveData = MovieRepository.getMovieDetail(1)
    val nowPlayingLiveData = MovieRepository.getNowPlaying()
    val popularLiveData = MovieRepository.getPopular()
    val topRatedLiveData = MovieRepository.getTopRated()
    val upcomingLiveData = MovieRepository.getUpcoming()

    init {
        with(errorLiveData) {
            addSource(configLiveData.errorLiveData)
            addSource(latestLiveData.errorLiveData)
            addSource(movieDetailLiveData.errorLiveData)
            addSource(nowPlayingLiveData.errorLiveData)
            addSource(popularLiveData.errorLiveData)
            addSource(topRatedLiveData.errorLiveData)
            addSource(upcomingLiveData.errorLiveData)
        }
    }

}