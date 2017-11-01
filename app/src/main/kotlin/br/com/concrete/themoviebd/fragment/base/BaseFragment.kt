package br.com.concrete.themoviebd.fragment.base

import android.support.v4.app.Fragment
import br.com.concrete.themoviebd.extension.toast

abstract class BaseFragment : Fragment() {

    open fun onErrorReceived(throwable: Throwable) {
        context?.toast(throwable.toString())
    }

}