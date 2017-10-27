package br.com.concrete.sdk.data.remote.interceptor

import br.com.concrete.sdk.data.remote.exception.*
import br.com.concrete.sdk.data.remote.gson
import br.com.concrete.sdk.model.SdkError
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

internal class ResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val code = response.code()

        if (response.isSuccessful) return response
        val path = request.url().encodedPath()

        if (code in 400..499) {
            val error = gson.fromJson(response.body()!!.string(), SdkError::class.java)
            throw when (code) {
                404 -> NotFoundException("Not Found Exception", code, path, error)
                403 -> ForbiddenException("Forbidden Exception", code, path, error)
                401 -> UnauthorizedException("Unauthorized Exception", code, path, error)
                400 -> BadRequestException("Bad Request Exception", code, path, error)
                else -> RemoteException("Unmapped Http Exception", code, path, error)
            }
        } else {
            throw ServerException("Server Exception", code, path)
        }
    }
}