package br.com.concrete.themoviebd.extension

import android.support.v4.app.FragmentTransaction
import br.com.concrete.themoviebd.base.BaseFragment

fun FragmentTransaction.detachIfHasDifferentTag(fragment: BaseFragment, tag: String): Boolean {
    if (tag == fragment.tag) return false
    detach(fragment)
    return true
}