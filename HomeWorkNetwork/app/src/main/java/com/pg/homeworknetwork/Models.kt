package com.pg.homeworknetwork
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movies (
        @SerialName("page")
        val page: Int,
        @SerialName("results")
        val movies: MutableList<Movie>
)

@Serializable
data class Movie (
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("poster_path")
        val posterPath: String,
        @SerialName("original_title")
        val originalTitle: String?,
        @SerialName("overview")
        val overview: String?,
        @SerialName("popularity")
        val popularity: Double?,
        @SerialName("release_date")
        val releaseDate: String?
)