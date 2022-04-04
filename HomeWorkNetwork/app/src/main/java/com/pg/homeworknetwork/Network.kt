package com.pg.homeworknetwork

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class Api {

    private val baseUrl = "${BuildConfig.API_BASE_URL}"
    private val movieDetailUrl = "3/movie/"
    private val popularMoviesUrl = "3/movie/popular"

    val kLogger = object : Logger {
        override fun log(message: String) {
            if (message.contains("api_key=")) {
                println("${message.split("=").first()}=BuildConfig.API_KEY")
            } else {
                println(message)
            }
        }
    }

    private val client = HttpClient() {

        install(Logging) {
            logger = kLogger
            level = LogLevel.BODY
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getMovie(movieId: Int): Movie = client.use {
        it.get(
            host = baseUrl,
            path = movieDetailUrl + movieId
        ) {
            parameter("api_key", BuildConfig.API_KEY)
        }
    }

    suspend fun getPopularMovies(): Movies? = client.use {
        it.get(
            host = baseUrl,
            path = popularMoviesUrl
        ) {
            parameter("api_key", BuildConfig.API_KEY)
        }
    }

}