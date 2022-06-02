package com.pg.homeworknetwork

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json as serialJson

class MovieApi {
    private val client = HttpClient(Android){
        install(JsonFeature) {
            serializer = KotlinxSerializer(serialJson {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getListMovies(): Movies = client.get(
            host = BuildConfig.API_BASE_URL,
            path = "3/movie/popular"
        ) {
            parameter("api_key", BuildConfig.API_KEY)
            parameter("language", "ru")
        }

    suspend fun getMovieById(id: Int): Movie = client.get(
            host = BuildConfig.API_BASE_URL,
            path = "3/movie/${id}"
        ) {
            parameter("api_key", BuildConfig.API_KEY)
            parameter("language", "ru")
        }
}