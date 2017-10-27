package br.com.concrete.themoviebd

import android.app.Application
import timber.log.Timber

class TheMovieDbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}