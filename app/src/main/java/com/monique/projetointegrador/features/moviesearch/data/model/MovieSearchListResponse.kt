package com.monique.projetointegrador.features.moviesearch.data.model

import com.google.gson.annotations.SerializedName

data class MovieSearchListResponse(
    @SerializedName("results")
    val movieResults: List<MovieSearchResponse>
)

data class MovieSearchResponse(
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
