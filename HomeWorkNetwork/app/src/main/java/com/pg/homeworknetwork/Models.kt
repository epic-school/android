package com.pg.homeworknetwork

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val page: Int,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<Movie>
) {}

@Serializable
data class Movie(
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String?,
//    val adult: Boolean?,
    val overview: String?,
    @SerialName("release_date")
    val releaseDate: String?,
//    @SerialName("genre_ids")
//    val genreIds: List<Int>? = null,
    @SerialName("original_title")
    val originalTitle: String?,
//    @SerialName("original_language")
//    val originalLanguage: String?,
    val title: String?,
//    @SerialName("backdrop_path")
//    val backdropPath: String?,
    val popularity: Float?,
//    @SerialName("vote_count")
//    val voteCount: Int?,
//    val video: Boolean?,
//    @SerialName("vote_average")
//    val voteAverage: Float?
) {}