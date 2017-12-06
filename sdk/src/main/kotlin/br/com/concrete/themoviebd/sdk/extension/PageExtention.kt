package br.com.concrete.themoviebd.sdk.extension

import br.com.concrete.themoviebd.sdk.model.Page

fun <T, R> Page<T>.map(transformation: (T) -> R) =
        Page(page, totalPages, totalResults, results.map(transformation), dates)
