package com.monique.projetointegrador.features.moviedetails.data.model

import com.google.gson.annotations.SerializedName
import com.monique.projetointegrador.domain.model.Genre

class MovieDetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("title")
    val title: String,
    var isFavorite: Boolean = false
)