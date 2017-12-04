package br.com.concrete.themoviebd.factory.viewmodel

import br.com.concrete.themoviebd.activity.base.BaseActivityTest
import br.com.concrete.themoviebd.feature.main.MainViewModel
import br.com.concrete.themoviebd.feature.movie.MovieSectionViewModel
import com.nhaarman.mockito_kotlin.whenever

fun BaseActivityTest<*>.mockMainViewModel(): MainViewModel {

    mockViewModelForClass(MovieSectionViewModel::class)

    val normalViewModel = MainViewModel()
    val viewModel = mockViewModelForClass(MainViewModel::class)
    whenever(viewModel.selectedItemId).thenReturn(normalViewModel.selectedItemId)
    whenever(viewModel.initialItemId).thenReturn(normalViewModel.initialItemId)
    whenever(viewModel.errorLiveData).thenReturn(normalViewModel.errorLiveData)

    return viewModel
}
