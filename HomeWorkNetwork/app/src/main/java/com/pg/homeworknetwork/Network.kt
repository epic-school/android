package com.pg.homeworknetwork
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
/*import kotlinx.coroutines.runBlocking*/
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

//@OptIn(ExperimentalSerializationApi::class)
class Api {
    private val _base = "api.themoviedb.org"
    private val _movies = "3/movie/popular"
    private val _movie = "3/movie/"


    //TODO добавить в предыдущий пример
    val kLogger = object : Logger {
        override fun log(message: String) {
            if (message.contains("api_key=")) {
                println("${message.split("=").first()}=BuildConfig.API_KEY")
            } else {
                println(message)
            }
        }
    }

    private val ktorClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = kLogger
            level = LogLevel.BODY
        }
    }

    suspend fun getMovie(movieId: Int): Movie = ktorClient.use {
        it.get(
            host = _base,
            path = _movie + movieId.toString()
        ) {
            parameter("api_key", BuildConfig.API_KEY)
        }
    }

    suspend fun getMovies(): Movies = ktorClient.use {
        it.get(
            host = _base,
            path = _movies
        ) {
            parameter("api_key", BuildConfig.API_KEY)
        }
    }



}