package br.com.concrete.themoviebd.base

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

}