package com.monique.projetointegrador.data.model.movies

import com.google.gson.annotations.SerializedName
import com.monique.projetointegrador.domain.Genre

class MovieDetailResponse(
    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("vote_average")
    val vote_average: Float,
    @SerializedName("title")
    val title: String,
)