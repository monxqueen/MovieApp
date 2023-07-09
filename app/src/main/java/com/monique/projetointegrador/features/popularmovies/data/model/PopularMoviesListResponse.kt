package com.monique.projetointegrador.features.popularmovies.data.model

import com.google.gson.annotations.SerializedName
import com.monique.projetointegrador.data.model.movies.MovieResponse

internal data class PopularMoviesListResponse(
    @SerializedName("results")
    val movieResults: List<PopularMovieResponse>
)

internal data class PopularMovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val imgHome: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    var isFavorite: Boolean = false
)