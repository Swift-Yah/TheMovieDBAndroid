package br.com.concrete.themoviebd.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.ConfigRepository
import br.com.concrete.sdk.MovieRepository

class MainViewModel : ViewModel() {

    val configLiveData = ConfigRepository.getConfiguration()

    val latestLiveData = MovieRepository.getLatestMovie()
    val movieDetailLiveData = MovieRepository.getMovieDetail(1)
    val nowPlayingLiveData = MovieRepository.getNowPlaying()
    val popularLiveData = MovieRepository.getPopular()
    val topRatedLiveData = MovieRepository.getTopRated()
    val upcomingLiveData = MovieRepository.getUpcoming()

}