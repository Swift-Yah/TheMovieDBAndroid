package br.com.concrete.themoviebd.extension

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseFragment

@Suppress("UNCHECKED_CAST")
fun <T : BaseFragment> BaseActivity.findFragment(@IdRes id: Int): T? {
    return supportFragmentManager.findFragmentById(id) as? T
}

@Suppress("UNCHECKED_CAST")
fun <T : BaseFragment> BaseActivity.findFragment(tag: String): T? {
    return supportFragmentManager.findFragmentByTag(tag) as? T
}

fun BaseActivity.clearFragmentStack() {
    supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun BaseActivity.fragmentTransaction(func: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply(func).commit()
}

fun BaseActivity.changeToFragment(tag: String, @IdRes parentId: Int, creator: (tag: String) -> BaseFragment) {
    fragmentTransaction {
        val currentFragment: BaseFragment? = findFragment(parentId)
        val mustResetFragment = currentFragment?.let { !detachIfHasDifferentTag(it, tag) && it.view != null } ?: false
        if (mustResetFragment) currentFragment?.reset()
        else {
            val oldFragment: BaseFragment? = findFragment(tag)
            oldFragment?.let(this::attach) ?: add(parentId, creator.invoke(tag), tag)
        }
    }
}
