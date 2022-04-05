package com.pg.homeworknetwork.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Класс для формирования ответа с сервера (/movies/popular)
 */
@Serializable
data class MoviesResponse(
    val page: Int = 0,
    val results: List<Movie> = emptyList(),
    @SerialName("total_results")
    val totalResults: Int = 0,
    @SerialName("total_pages")
    val totalPages: Int = 0
)