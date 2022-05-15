package com.pg.homeworknetwork

import kotlinx.serialization.*

@Serializable
class Movies
(
    @SerialName("page")
    val page: Int,
    @SerialName("result")
    val result : List<Movie> =ArrayList(),
    @SerialName("total_results")
    val total_results: Int,
    @SerialName("total_pages")
    val total_pages:  Int
)


@Serializable
data class Movie
    (
    @SerialName("id") //TODO рассказать
    val id: Int,
    @SerialName("title")
    val title: String? = null,
    @SerialName("poster")
    val poster: String? = null,
    //
    @SerialName("poster_patch")
    val posterPath: String? = null,
    //
    @SerialName("original_title")
    val originalTitle: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("release_date")
    val releaseDate: String? = null
)
