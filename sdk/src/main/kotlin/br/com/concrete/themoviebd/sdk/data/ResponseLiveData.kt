package br.com.concrete.themoviebd.sdk.data

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.concrete.themoviebd.sdk.extension.observe
import br.com.concrete.themoviebd.sdk.extension.observeSingle
import br.com.concrete.themoviebd.sdk.extension.observeUntil
import br.com.concrete.themoviebd.sdk.model.DataResult
import br.com.concrete.themoviebd.sdk.model.type.ERROR
import br.com.concrete.themoviebd.sdk.model.type.LOADING
import br.com.concrete.themoviebd.sdk.model.type.SUCCESS
import java.util.concurrent.atomic.AtomicBoolean

abstract class ResponseLiveData<T> : LiveData<DataResult<T>>() {

    private val computed = AtomicBoolean(false)

    val errorLiveData = MutableLiveData<Throwable>()

    val loadingLiveData = MutableLiveData<Boolean>()

    fun observeSingle(owner: LifecycleOwner, observer: (data: DataResult<T>) -> Unit) = observeUntil(owner) {
        it.let(observer)
        it.status != LOADING
    }

    fun observeData(owner: LifecycleOwner, success: (data: T) -> Unit) = observe(owner) {
        when (it.status) {
            ERROR -> errorLiveData.value = it.error
            SUCCESS -> success(it.data ?: throw IllegalStateException("Data null! =("))
        }
        loadingLiveData.value = it.status == LOADING
    }

    fun observeLoading(owner: LifecycleOwner, loading: (loading: Boolean) -> Unit) = loadingLiveData.observe(owner, loading)

    fun observeError(owner: LifecycleOwner, error: (error: Throwable) -> Unit) = errorLiveData.observe(owner, error)

    fun observeSingleData(owner: LifecycleOwner, success: (data: T) -> Unit) = observeUntil(owner) {
        when (it.status) {
            ERROR -> errorLiveData.value = it.error
            SUCCESS -> success(it.data!!)
        }
        loadingLiveData.value = it.status == LOADING

        it.status != LOADING
    }

    fun observeSingleError(owner: LifecycleOwner, error: (error: Throwable) -> Unit) = errorLiveData.observeSingle(owner, error)

    fun observeSingleLoading(owner: LifecycleOwner, loading: (loading: Boolean) -> Unit) = loadingLiveData.observeSingle(owner, loading)

    fun getData() = value?.data

    fun getError() = value?.error

    fun getStatus() = value?.status

    abstract fun compute()

    open fun invalidate() = compute()

    override fun onActive() {
        super.onActive()
        if (computed.compareAndSet(false, true)) compute()
    }

}