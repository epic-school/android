package com.pg.homeworknetwork.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Класс для описание фильма
 */
@Serializable
data class Movie(
    val id: Int = 0,
    val title: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("poster_path")
    val posterPath: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    @SerialName("release_date")
    val releaseDate: String = ""
)