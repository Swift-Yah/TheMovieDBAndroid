package br.com.concrete.themoviebd.delegate

import android.arch.lifecycle.ViewModelProviders
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseFragment
import br.com.concrete.themoviebd.base.BaseViewModel
import br.com.concrete.themoviebd.sdk.extension.observe
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ViewModelProviderDelegate<out T : BaseViewModel>(private val clazz: KClass<T>, private val fromActivity: Boolean) {

    private var viewModel: T? = null

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>) = buildViewModel(activity = thisRef)

    operator fun getValue(thisRef: BaseFragment, property: KProperty<*>) = if (fromActivity)
        buildViewModel(activity = thisRef.activity as? BaseActivity ?: throw IllegalStateException("Activity must be as BaseActivity"))
    else buildViewModel(fragment = thisRef)

    private fun buildViewModel(activity: BaseActivity? = null, fragment: BaseFragment? = null): T {
        if (viewModel != null) return viewModel!!

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(clazz.java)
            viewModel!!.errorLiveData.observe(it, it::onErrorReceived)
        } ?: fragment?.let {
            viewModel = ViewModelProviders.of(it).get(clazz.java)
            viewModel!!.errorLiveData.observe(it, it::onErrorReceived)
        } ?: throw IllegalStateException("Activity and Fragment null! =(")

        return viewModel!!
    }
}

fun <T : BaseViewModel> BaseActivity.viewModelProvider(clazz: KClass<T>) =
        ViewModelProviderDelegate(clazz, false)

fun <T : BaseViewModel> BaseFragment.viewModelProvider(clazz: KClass<T>, fromActivity: Boolean = false) =
        ViewModelProviderDelegate(clazz, fromActivity)