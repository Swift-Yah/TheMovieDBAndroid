package br.com.concrete.themoviebd.delegate

import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.VisibleForTesting
import br.com.concrete.sdk.extension.observe
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseFragment
import br.com.concrete.themoviebd.base.BaseViewModel
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ViewModelProviderDelegate<T : BaseViewModel>(private val clazz: KClass<T>, private val fromActivity: Boolean) {

    var viewModel: T? = null
        @VisibleForTesting set

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

    companion object {
        fun <T : BaseViewModel> create(clazz: KClass<T>, fromActivity: Boolean) =
                ViewModelProviderDelegate(clazz, fromActivity)
    }
}

fun <T : BaseViewModel> BaseActivity.viewModelProvider(clazz: KClass<T>) =
        ViewModelProviderDelegate.create(clazz, false)

fun <T : BaseViewModel> BaseFragment.viewModelProvider(clazz: KClass<T>, fromActivity: Boolean = false) =
        ViewModelProviderDelegate.create(clazz, fromActivity)