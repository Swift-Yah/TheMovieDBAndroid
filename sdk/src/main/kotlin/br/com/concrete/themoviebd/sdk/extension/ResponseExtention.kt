@file:JvmName("ResponseUtils")

package br.com.concrete.themoviebd.sdk.extension

import br.com.concrete.themoviebd.sdk.model.DataResult
import br.com.concrete.themoviebd.sdk.model.type.DataResultStatus
import br.com.concrete.themoviebd.sdk.model.type.ERROR
import br.com.concrete.themoviebd.sdk.model.type.LOADING

internal fun loadingResponse() = DataResult(null, null, LOADING)

internal fun <T> T?.toDataResponse(@DataResultStatus status: Long) = DataResult(this, null, status)

internal fun <T> T?.toDataResponseWithError(error: Throwable) = DataResult(this, error, ERROR)

internal fun <T> Throwable.toErrorResponse() = DataResult<T>(null, this, ERROR)