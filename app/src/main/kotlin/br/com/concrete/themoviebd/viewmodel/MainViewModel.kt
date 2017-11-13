package br.com.concrete.themoviebd.viewmodel

import br.com.concrete.sdk.MovieRepository
import br.com.concrete.themoviebd.transform.toMediaItemPage
import br.com.concrete.themoviebd.viewmodel.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val nowPlayingLiveData = MovieRepository.getNowPlaying().toMediaItemPage()
    val popularLiveData = MovieRepository.getPopular().toMediaItemPage()
    val topRatedLiveData = MovieRepository.getTopRated().toMediaItemPage()
    val upcomingLiveData = MovieRepository.getUpcoming().toMediaItemPage()

}