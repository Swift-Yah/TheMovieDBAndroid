package br.com.concrete.themoviebd.extension

import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.type.ERROR
import br.com.concrete.sdk.model.type.LOADING
import br.com.concrete.sdk.model.type.SUCCESS
import com.nhaarman.mockito_kotlin.whenever

fun <T> ResponseLiveData<T>?.mockValue(value: T) = mockResponse(DataResult(value, null, SUCCESS)).data!!
fun <T> ResponseLiveData<T>?.mockLoading() = mockResponse(DataResult(null, null, LOADING))
fun <T> ResponseLiveData<T>?.mockError(error: Throwable) = mockResponse(DataResult(null, error, ERROR)).error!!

fun <T> ResponseLiveData<T>?.mockResponse(value: DataResult<T>): DataResult<T> {
    whenever(this).thenReturn(object : ResponseLiveData<T>() {
        init {
            postValue(value)
        }

        override fun compute() {
        }
    })
    return value
}
