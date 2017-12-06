package br.com.concrete.themoviebd.sdk.model

import br.com.concrete.themoviebd.sdk.model.type.DataResultStatus

data class DataResult<out T>(
        val data: T?,
        var error: Throwable?,
        @get:DataResultStatus
        @DataResultStatus val status: Long
)