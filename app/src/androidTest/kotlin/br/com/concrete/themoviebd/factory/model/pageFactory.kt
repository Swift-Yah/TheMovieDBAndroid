package br.com.concrete.themoviebd.factory.model

import br.com.concrete.themoviebd.sdk.model.Page

fun <T> emptyPage(): Page<T> {
    return pageWith(emptyList())
}

fun <T> pageWith(results: List<T>): Page<T> {
    return Page(page = 0,
            totalPages = 0,
            totalResults = 0,
            results = results,
            dates = null)
}