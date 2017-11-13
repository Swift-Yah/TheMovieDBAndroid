package br.com.concrete.themoviebd.delegate

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.concrete.themoviebd.activity.base.BaseActivity
import br.com.concrete.themoviebd.fragment.base.BaseFragment
import kotlin.reflect.KProperty

class ViewProviderDelegate<out T : View>(@IdRes private val idRes: Int) {

    private var view: T? = null

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>): T {
        view = getValue(view, idRes, thisRef)
        return view!!
    }

    operator fun getValue(thisRef: BaseFragment, property: KProperty<*>): T {
        view = getValue(view, idRes, thisRef)
        return view!!
    }

    operator fun getValue(thisRef: View, property: KProperty<*>): T {
        view = getValue(view, idRes, thisRef)
        return view!!
    }

    operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
        view = getValue(view, idRes, thisRef)
        return view!!
    }

}

class NullableViewProviderDelegate<out T : View>(@IdRes private val idRes: Int) {

    private var view: T? = null

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>): T? {
        view = getValue(view, idRes, thisRef)
        return view
    }

    operator fun getValue(thisRef: BaseFragment, property: KProperty<*>): T? {
        view = getValue(view, idRes, thisRef)
        return view
    }

    operator fun getValue(thisRef: View, property: KProperty<*>): T? {
        view = getValue(view, idRes, thisRef)
        return view
    }

    operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T? {
        view = getValue(view, idRes, thisRef)
        return view
    }

}

fun <T : View> viewProvider(@IdRes idRes: Int) = ViewProviderDelegate<T>(idRes)

fun <T : View> nullableViewProvider(@IdRes idRes: Int) = NullableViewProviderDelegate<T>(idRes)

private fun <T : View> getValue(oldView: T?, @IdRes idRes: Int, thisRef: BaseActivity): T? =
        oldView ?: thisRef.findViewById(idRes)

private fun <T : View> getValue(oldView: T?, @IdRes idRes: Int, thisRef: BaseFragment): T? =
        oldView ?: thisRef.view?.findViewById(idRes)

private fun <T : View> getValue(oldView: T?, @IdRes idRes: Int, thisRef: View): T? =
        oldView ?: thisRef.findViewById(idRes)

private fun <T : View> getValue(oldView: T?, @IdRes idRes: Int, thisRef: RecyclerView.ViewHolder): T? =
        oldView ?: thisRef.itemView.findViewById(idRes)