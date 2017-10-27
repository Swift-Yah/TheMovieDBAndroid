package br.com.concrete.themoviebd.delegate

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ViewModelProviderDelegate<out T : ViewModel>(private val clazz: KClass<T>) {

    private var viewModel: T? = null

    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(thisRef).get(clazz.java)
        }
        return viewModel!!
    }

}

fun <T : ViewModel> viewModelProvider(clazz: KClass<T>) = ViewModelProviderDelegate(clazz)