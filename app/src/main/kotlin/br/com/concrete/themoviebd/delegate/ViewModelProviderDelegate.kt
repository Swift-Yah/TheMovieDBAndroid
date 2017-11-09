package br.com.concrete.themoviebd.delegate

import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.VisibleForTesting
import br.com.concrete.sdk.extension.observe
import br.com.concrete.themoviebd.activity.base.BaseActivity
import br.com.concrete.themoviebd.fragment.base.BaseFragment
import br.com.concrete.themoviebd.viewmodel.base.BaseViewModel
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ViewModelProviderDelegate<T : BaseViewModel>(private val clazz: KClass<T>, private val activity: BaseActivity? = null) {

    var viewModel: T? = null
        @VisibleForTesting set

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>) = buildViewModel(activity = thisRef)

    operator fun getValue(thisRef: BaseFragment, property: KProperty<*>) = buildViewModel(fragment = thisRef)

    private fun buildViewModel(activity: BaseActivity? = this.activity, fragment: BaseFragment? = null): T {
        if (viewModel != null) return viewModel!!

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(clazz.java)
            viewModel!!.errorLiveData.observe(it, it::onErrorReceived)
        } ?: fragment?.let {
            viewModel = ViewModelProviders.of(it).get(clazz.java)
            viewModel!!.errorLiveData.observe(it, it::onErrorReceived)
        }

        return viewModel!!
    }

    companion object {
        fun <T : BaseViewModel> create(clazz: KClass<T>, activity: BaseActivity? = null) =
                ViewModelProviderDelegate(clazz, activity)
    }
}

fun <T : BaseViewModel> viewModelProvider(clazz: KClass<T>, activity: BaseActivity? = null) =
        ViewModelProviderDelegate.create(clazz, activity)