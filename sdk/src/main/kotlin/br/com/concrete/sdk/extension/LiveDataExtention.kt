@file:JvmName("LiveDataUtils")

package br.com.concrete.sdk.extension

import android.arch.lifecycle.*
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.type.ERROR
import br.com.concrete.sdk.model.type.LOADING
import br.com.concrete.sdk.model.type.SUCCESS

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
            value = when (result.status) {
                SUCCESS -> result.data?.let(transformation).toDataResponse(SUCCESS)
                ERROR -> result.error?.toErrorResponse()
                LOADING -> loadingResponse()
                else -> null
            }
        }
    }
}

fun <T, R> ResponseLiveData<Page<T>>.mapPage(transformation: (T) -> R) =
        map { page -> page.map(transformation) }