package br.com.concrete.themoviebd.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.concrete.themoviebd.extension.toast

abstract class BaseFragment(@LayoutRes private val layoutRes: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutRes, container, false)
    }

    open fun onErrorReceived(throwable: Throwable) {
        context?.toast(throwable)
    }

    open fun handleBack(): Boolean = false

    open fun reset() = Unit

    /**
     * That is necessary because, by default,
     * the fragment does not call the ON_DESTROY event
     * when is detached from the activity.
     *
     * Then observers are called twice when fragments are attached and detached
     * Causing memory leaks
     *
     * =(
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (lifecycle as? LifecycleRegistry)?.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (lifecycle as? LifecycleRegistry)?.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

}