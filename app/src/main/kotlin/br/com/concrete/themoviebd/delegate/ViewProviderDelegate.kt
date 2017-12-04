package br.com.concrete.themoviebd.delegate

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseFragment
import kotlin.reflect.KProperty

class ViewProviderDelegate<out T : View>(@IdRes idRes: Int) : NullableViewProviderDelegate<T>(idRes) {

    override operator fun getValue(thisRef: BaseActivity, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }

    override operator fun getValue(thisRef: BaseFragment, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }

    override operator fun getValue(thisRef: View, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }

    override operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }

}

open class NullableViewProviderDelegate<out T : View>(@IdRes private val idRes: Int) {

    private var view: T? = null

    open operator fun getValue(thisRef: BaseActivity, property: KProperty<*>): T? {
        view?.let { if (!it.isAttachedToWindow) view = null }
        view = view ?: thisRef.findViewById(idRes)
        return view
    }

    open operator fun getValue(thisRef: BaseFragment, property: KProperty<*>): T? {
        view?.let { if (!it.isAttachedToWindow) view = null }
        view = view ?: thisRef.view?.findViewById(idRes)
        return view
    }

    open operator fun getValue(thisRef: View, property: KProperty<*>): T? {
        view = view ?: thisRef.findViewById(idRes)
        return view
    }

    open operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T? {
        view = view ?: thisRef.itemView.findViewById(idRes)
        return view
    }

}

fun <T : View> viewProvider(@IdRes idRes: Int) = ViewProviderDelegate<T>(idRes)

fun <T : View> nullableViewProvider(@IdRes idRes: Int) = NullableViewProviderDelegate<T>(idRes)