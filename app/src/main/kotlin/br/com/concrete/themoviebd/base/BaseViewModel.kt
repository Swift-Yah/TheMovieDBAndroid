package br.com.concrete.themoviebd.base

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val errorLiveData = MediatorLiveData<Throwable>()

}