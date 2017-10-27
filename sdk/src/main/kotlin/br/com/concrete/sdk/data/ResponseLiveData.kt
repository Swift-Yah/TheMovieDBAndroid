package br.com.concrete.sdk.data

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import br.com.concrete.sdk.extension.observe
import br.com.concrete.sdk.extension.observeSingle
import br.com.concrete.sdk.extension.observeUntil
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.type.ERROR
import br.com.concrete.sdk.model.type.LOADING

abstract class ResponseLiveData<T> : LiveData<DataResult<T>>() {

    val errorLiveData = MutableLiveData<Throwable>()

    val loadingLiveData = MutableLiveData<Boolean>()

    fun observeData(owner: LifecycleOwner, success: (data: T?) -> Unit) = observe(owner) {
        if (it.status != ERROR) success(it.data)
        else errorLiveData.value = it.error

        loadingLiveData.value = it.status == LOADING
    }

    fun observeSingle(owner: LifecycleOwner, observer: (data: DataResult<T>) -> Unit) = observeUntil(owner) {
        it.let(observer)
        it.status != LOADING
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<DataResult<T>>) {
        super.observe(owner, observer)
    }

//TODO Será?
//    fun observe(owner: LifecycleOwner,
//                shablau: Lala<T>) {}

    fun observeLoading(owner: LifecycleOwner, loading: (loading: Boolean) -> Unit) = loadingLiveData.observe(owner, loading)

    fun observeError(owner: LifecycleOwner, error: (error: Throwable) -> Unit) = errorLiveData.observe(owner, error)

    fun observeSingleData(owner: LifecycleOwner, success: (data: T?) -> Unit) = observeUntil(owner) {
        if (it.status != ERROR) success(it.data)
        else errorLiveData.value = it.error

        loadingLiveData.value = it.status == LOADING

        it.status != LOADING
    }

    open fun invalidate() {
        value = null
    }

    fun observeSingleError(owner: LifecycleOwner, error: (error: Throwable) -> Unit) = errorLiveData.observeSingle(owner, error)

    fun observeSingleLoading(owner: LifecycleOwner, loading: (loading: Boolean) -> Unit) = loadingLiveData.observeSingle(owner, loading)

    fun getData() = value?.data

    fun getError() = value?.error

    fun getStatus() = value?.status
}

//TODO Será?
//val userLiveData: ResponseLiveData<String> = object : ResponseLiveData<String>() {}
//
//fun lala(lifecycleOwner: LifecycleOwner) {
//
//
//    userLiveData.observe(lifecycleOwner) {
//    } // Dado Cru
//
//    userLiveData.observeData(lifecycleOwner) {
//    } // Apenas o resultado
//
//    userLiveData.observeSingleData(lifecycleOwner) {
//    } // Apenas o resultado uma vez
//
//    userLiveData.observeError(lifecycleOwner) {
//    } // Apenas o Erro
//
//    userLiveData.observeSingleError(lifecycleOwner) {
//    } // Apenas o Erro uma vez
//
//    userLiveData.observe(lifecycleOwner, shablau = object : Lala<String>() {
//        override fun onSuccess(data: String?) {
//        }
//
//        override fun onError(data: Throwable) {
//        }
//
//        override fun onLoading(data: Boolean) {
//        }
//    })
//}
//
//abstract class Lala<in T>{
//    open fun onSuccess(data: T?){}
//    open fun onError(data: Throwable){}
//    open fun onLoading(data: Boolean){}
//}