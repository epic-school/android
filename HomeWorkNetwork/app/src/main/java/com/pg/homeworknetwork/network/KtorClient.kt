package com.pg.homeworknetwork.network

import com.pg.homeworknetwork.BuildConfig
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

/**
 * Ktor клиент
 */
object KtorClient {
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    if (message.contains("api_key"))
                        println(message.replaceAfter("api_key=", "api_key"))
                    else println(message)
                }
            }
            level = LogLevel.ALL
        }

        defaultRequest {
            host = BuildConfig.API_BASE_URL
            url {
                protocol = URLProtocol.HTTPS
            }
            parameter("api_key", BuildConfig.API_KEY)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}