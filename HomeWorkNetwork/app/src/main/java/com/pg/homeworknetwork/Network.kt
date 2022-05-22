package com.pg.homeworknetwork

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class Api {
    companion object Config {
        private const val BASE_URL = BuildConfig.API_BASE_URL
        private const val USERS = "/api/users"
        private const val USER = "/api/users"
    }

    private val client = HttpClient(Android) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // https://reqres.in/api/users
    suspend fun getUsers(): Users = client.get(host = BASE_URL, path = USERS)

    // https://reqres.in/api/users/2
    suspend fun getUser(userId: Int): Data =
        client.get(host = BASE_URL, path = "$USER/$userId")
}