package br.com.concrete.themoviebd.sdk.data.remote

import br.com.concrete.themoviebd.sdk.BuildConfig
import br.com.concrete.themoviebd.sdk.data.ResponseLiveData
import br.com.concrete.themoviebd.sdk.data.remote.factory.LiveDataCallAdapterFactory
import br.com.concrete.themoviebd.sdk.data.remote.interceptor.AuthInterceptor
import br.com.concrete.themoviebd.sdk.data.remote.interceptor.ResponseInterceptor
import br.com.concrete.themoviebd.sdk.model.domain.Movie
import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.sdk.model.domain.SdkConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import timber.log.Timber

internal interface TheMovieDbApi {

    @GET("configuration")
    fun getConfiguration(): ResponseLiveData<SdkConfig>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: Int): ResponseLiveData<Movie>

    @GET("movie/latest")
    fun getLatestMovie(): ResponseLiveData<Movie>

    @GET("movie/now_playing")
    fun getNowPlaying(@Query("page") page: Int): ResponseLiveData<Page<Movie>>

    @GET("movie/popular")
    fun getPopular(@Query("page") page: Int): ResponseLiveData<Page<Movie>>

    @GET("movie/top_rated")
    fun getTopRated(@Query("page") page: Int): ResponseLiveData<Page<Movie>>

    @GET("movie/upcoming")
    fun getUpcoming(@Query("page") page: Int): ResponseLiveData<Page<Movie>>

}

internal val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

internal val apiInstance: TheMovieDbApi by lazy {
    Retrofit.Builder()
            .addConverterFactory(buildGson())
            .client(buildClient())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(BuildConfig.BASE_URL).build().create(TheMovieDbApi::class.java)
}

private fun buildClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor { Timber.i(it) }
            .setLevel(Level.BODY)
    return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(logging)
            .addInterceptor(ResponseInterceptor())
            .build()
}

private fun buildGson() = GsonConverterFactory.create(gson)