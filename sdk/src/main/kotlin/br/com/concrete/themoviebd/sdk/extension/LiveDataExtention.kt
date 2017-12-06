@file:JvmName("LiveDataUtils")

package br.com.concrete.themoviebd.sdk.extension

import android.arch.lifecycle.*
import br.com.concrete.themoviebd.sdk.data.ResponseLiveData
import br.com.concrete.themoviebd.sdk.model.DataResult
import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.sdk.model.type.ERROR
import br.com.concrete.themoviebd.sdk.model.type.LOADING
import br.com.concrete.themoviebd.sdk.model.type.SUCCESS

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: ((T) -> Unit)) = observe(owner, Observer { it?.let(observer) })

fun <T> LiveData<T>.observeSingle(owner: LifecycleOwner, observer: ((T) -> Unit)) = observeUntil(owner) {
    it.let(observer)
    true
}

fun <T> LiveData<T>.observeUntil(owner: LifecycleOwner, observer: ((T) -> Boolean)) {
    observe(owner, object : Observer<T> {
        override fun onChanged(data: T?) {
            if (data?.let(observer) == true) removeObserver(this)
        }
    })
}

fun <T> MediatorLiveData<T>.addSource(source: LiveData<T>) = addSource(source) {
    value = it
}

fun <T> MediatorLiveData<T>.addSources(vararg sources: LiveData<T>, observer: (T?) -> Unit) {
    sources.forEach {
        addSource(it, observer)
    }
}

fun <T> LiveData<T>.and(another: LiveData<T>, withResult: (fromThis: T, fromAnother: T) -> T = { fromThis: T, _: T -> fromThis }): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    var firstResult: T? = null

    mediator.addSource(this) { value: T? ->
        value?.let {
            firstResult?.let {
                mediator.value = withResult.invoke(value, it)
                firstResult = null
            }
            if (firstResult == null) firstResult = it
        }
    }
    mediator.addSource(another) { value: T? ->
        value?.let {
            firstResult?.let {
                mediator.value = withResult.invoke(it, value)
                firstResult = null
            }
            if (firstResult == null) firstResult = it
        }
    }
    return mediator
}

infix operator fun <T> LiveData<T>.plus(liveData: LiveData<T>): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this)
    mediator.addSource(liveData)
    return mediator
}

fun <T> ResponseLiveData<T>.toSimpleLiveData(): LiveData<T> {
    val newLiveData = MutableLiveData<T>()
    return Transformations.switchMap(this) {
        it.data?.let { newLiveData.value = it }
        newLiveData
    }
}

fun <T, R> ResponseLiveData<T>.map(transformation: (T) -> R) = object : ResponseLiveData<R>() {
    override fun compute() = Unit
    override fun invalidate() = this@map.invalidate()

    override fun observe(owner: LifecycleOwner, observer: Observer<DataResult<R>>) {
        super.observe(owner, observer)
        this@map.observe(owner) { result ->
            val newValue = when (result.status) {
                SUCCESS -> result.data?.let(transformation).toDataResponse(SUCCESS)
                ERROR -> result.error?.toErrorResponse()
                LOADING -> loadingResponse()
                else -> null
            }
            if (value != newValue) value = newValue
        }
    }

}

fun <T, R> ResponseLiveData<Page<T>>.mapPage(transformation: (T) -> R) =
        map { page -> page.map(transformation) }