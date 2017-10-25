package br.com.concrete.sdk.data.remote.interceptor

import br.com.concrete.sdk.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain.request()) {
        val url = with(url()) {
            if (queryParameterNames().contains("api_key")) this
            else newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()
        }
        chain.proceed(newBuilder().url(url).build())
    }
}