@file:JvmName("LiveDataUtils")

package br.com.concrete.sdk.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.model.DataResult
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

fun <T, R> ResponseLiveData<T>.mapData(transformation: (T) -> R): ResponseLiveData<R> {
    return object : ResponseLiveData<R>() {
        override fun compute() {
            this@mapData.observeForever(object : Observer<DataResult<T>> {
                override fun onChanged(data: DataResult<T>?) {
                    value = when (data?.status) {
                        SUCCESS -> data.data?.let(transformation).toDataResponse(SUCCESS)
                        ERROR -> data.error?.toErrorResponse()
                        LOADING -> loadingResponse()
                        else -> null
                    }
                    if (data?.status != LOADING) removeObserver(this)
                }

            })
        }

        override fun invalidate() {
            this@mapData.invalidate()
        }
    }
}

