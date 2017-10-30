package br.com.concrete.sdk.model

import com.google.gson.annotations.Expose

data class DateRange(
        @Expose val maximum: String,
        @Expose val minimum: String
)