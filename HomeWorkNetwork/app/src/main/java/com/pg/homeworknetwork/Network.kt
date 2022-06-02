package com.pg.homeworknetwork

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path

@OptIn(ExperimentalSerializationApi::class)

object ApiObj {
    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }
    private val keyInterceptor = Interceptor { chain ->
        val url: HttpUrl = chain.request().url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(keyInterceptor)
    }.build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()
    private val api: Api = retrofit.create(Api::class.java)

    fun getApi() : Api = api
}

interface Api {
    @GET("3/movie/{id}")
    fun getMovie(@Path("id") id: Int): Single<Movie>

    @GET("3/movie/popular")
    fun getMostPopularMovies(): Single<Movies>
}