package br.com.concrete.themoviebd.delegate

import br.com.concrete.themoviebd.activity.base.BaseActivity
import br.com.concrete.themoviebd.fragment.base.BaseFragment
import kotlin.reflect.KProperty

class ExtraProviderDelegate<out T>(private val extraName: String) {

    private var extra: T? = null

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>): T {
        extra = getExtra(extra, extraName, thisRef)
        return extra!!
    }

    operator fun getValue(thisRef: BaseFragment, property: KProperty<*>): T {
        extra = getExtra(extra, extraName, thisRef)
        return extra!!
    }

}

class NullableExtraProviderDelegate<out T>(private val extraName: String) {

    private var extraValue: T? = null

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>): T? {
        extraValue = getExtra(extraValue, extraName, thisRef)
        return extraValue
    }

    operator fun getValue(thisRef: BaseFragment, property: KProperty<*>): T? {
        extraValue = getExtra(extraValue, extraName, thisRef)
        return extraValue
    }

}

fun <T> extraProvider(extra: String) = ExtraProviderDelegate<T>(extra)
fun <T> nullableExtraProvider(extra: String) = NullableExtraProviderDelegate<T>(extra)

@Suppress("UNCHECKED_CAST")
private fun <T> getExtra(oldExtra: T?, extraName: String, thisRef: BaseActivity): T? =
        oldExtra ?: thisRef.intent?.extras?.get(extraName) as T?

@Suppress("UNCHECKED_CAST")
private fun <T> getExtra(oldExtra: T?, extraName: String, thisRef: BaseFragment): T? =
        oldExtra ?: thisRef.arguments?.get(extraName) as T?