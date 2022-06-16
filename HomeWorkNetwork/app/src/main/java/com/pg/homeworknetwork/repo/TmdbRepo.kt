package com.pg.homeworknetwork.repo

import com.pg.homeworknetwork.model.Movie
import com.pg.homeworknetwork.model.MoviesResponse
import com.pg.homeworknetwork.network.KtorClient.httpClient
import io.ktor.client.features.*
import io.ktor.client.request.*

/**
 * Repo для запросов
 */
object TmdbRepo {

    /** Получает список фильмов [MoviesResponse] */
    suspend fun getMovies(): MoviesResponse? =
        try {
            httpClient.get(path = "movie/popular")
        } catch (e: ResponseException) {
            println(e.response.status.description)
            null
        }

    /** Получает фильм [Movie] по идентификатору [id] */
    suspend fun getMovie(id: Int): Movie? =
        try {
            httpClient.get(path = "/movie/$id")
        } catch (e: ResponseException) {
            println(e.response.status.description)
            null
        }
}