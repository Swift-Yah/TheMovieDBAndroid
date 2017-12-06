package br.com.concrete.themoviebd.sdk.model

import com.google.gson.annotations.Expose

data class Page<out T>(
        @Expose val page: Int,
        @Expose val totalPages: Int,
        @Expose val totalResults: Int,
        @Expose val results: List<T>,
        @Expose val dates: DateRange?
)