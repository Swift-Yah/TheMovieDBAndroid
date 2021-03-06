package br.com.concrete.themoviebd.sdk.data.remote.factory

import android.os.AsyncTask
import br.com.concrete.themoviebd.sdk.data.ResponseLiveData
import br.com.concrete.themoviebd.sdk.extension.loadingResponse
import br.com.concrete.themoviebd.sdk.extension.toDataResponse
import br.com.concrete.themoviebd.sdk.extension.toErrorResponse
import br.com.concrete.themoviebd.sdk.model.DataResult
import br.com.concrete.themoviebd.sdk.model.type.SUCCESS
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != ResponseLiveData::class.java) return null

        val type = getParameterUpperBound(0, returnType as ParameterizedType)
        return LiveDataCallAdapter<Any>(type)
    }
}

internal class LiveDataCallAdapter<RESULT>(private val responseType: Type) : CallAdapter<RESULT, ResponseLiveData<RESULT>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<RESULT>) = object : ResponseLiveData<RESULT>() {
        override fun compute() {
            value = loadingResponse()
            RequestMaker(this::setValue).execute(call)
        }
    }
}

private class RequestMaker<RESULT>(val onRequestFinish: (DataResult<RESULT>) -> Unit) : AsyncTask<Call<RESULT>, Unit, DataResult<RESULT>>() {

    override fun doInBackground(vararg calls: Call<RESULT>): DataResult<RESULT> = try {
        val response = if (calls[0].isExecuted) calls[0].clone().execute() else calls[0].execute()
        val data = response.body()

        data.toDataResponse(SUCCESS)
    } catch (error: Exception) {
        error.toErrorResponse()
    }

    override fun onPostExecute(result: DataResult<RESULT>) = onRequestFinish.invoke(result)
}