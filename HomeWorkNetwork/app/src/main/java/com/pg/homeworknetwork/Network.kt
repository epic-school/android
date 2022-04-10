package com.pg.homeworknetwork

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class Api {
    companion object {
        const val BASE_URL = BuildConfig.API_BASE_URL
        const val POPULAR_MOVIES = "3/movie/popular"
        const val MOVIE_DETAILS = "3/movie/"

        fun create(): MovieService {
            return MovieServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }
                })
        }
    }
}

interface MovieService {
    suspend fun getPopularMovies(): Movies

    suspend fun getMovieById(id: Int): Movie
}

class MovieServiceImpl(
    private val client: HttpClient
) : MovieService {
    override suspend fun getPopularMovies(): Movies {
        return client.use {
            it.get(host = Api.BASE_URL, path = Api.POPULAR_MOVIES) {
                parameter("api_key", BuildConfig.API_KEY)
            }
        }
    }

    override suspend fun getMovieById(id: Int): Movie {
        return client.use {
            it.get(host = Api.BASE_URL, path = Api.MOVIE_DETAILS + id.toString()) {
                parameter("api_key", BuildConfig.API_KEY)
            }
        }
    }
}