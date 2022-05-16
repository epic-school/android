package com.pg.homeworknetwork

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json as serialJson

class Api {
    private val client = HttpClient(Android){
        install(JsonFeature) {
            serializer = KotlinxSerializer(serialJson {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getTopMovies(): Movies {
        return client.use {
            it.get(host = BuildConfig.API_BASE_URL, path = "3/movie/top_rated") {
                parameter("api_key", BuildConfig.API_KEY)
            }
        }
    }

    suspend fun getMovieById(id: Int): Movie {
        return client.use {
            it.get(host = BuildConfig.API_BASE_URL, path = "3/movie/" + id.toString()) {
                parameter("api_key", BuildConfig.API_KEY)
            }
        }
    }

}