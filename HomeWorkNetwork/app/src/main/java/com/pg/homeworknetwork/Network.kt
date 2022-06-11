package com.pg.homeworknetwork

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

// https://api.themoviedb.org/3/movie/{movie_id}?api_key=API_KEY (6259c72d2e1c19b81663e5e9e9ef7356)
// https://api.themoviedb.org/3/movie/550?api_key=6259c72d2e1c19b81663e5e9e9ef7356

class Api {
    companion object {
        private const val BASE_URL = BuildConfig.API_BASE_URL
        const val TOP_RATED_MOVIES = "3/movie/top_rated"
        const val MOVIE_DETAILS = "3/movie/"
    }

    private val client = HttpClient(Android) {
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
    }

    suspend fun getTopRatedMovies(): Movies {
        return client.use {
            it.get(host = BASE_URL, path = TOP_RATED_MOVIES) {
                parameter("api_key", BuildConfig.API_KEY)
            }
        }
    }

    suspend fun getMovieById(id: Int): Movie {
        return client.use {
            it.get(host = BASE_URL, path = MOVIE_DETAILS + id.toString()) {
                parameter("api_key", BuildConfig.API_KEY)
            }
        }
    }
}