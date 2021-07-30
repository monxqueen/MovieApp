package com.monique.projetointegrador.data.model.movies

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val imgHome: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val rating: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    var isFavorite: Boolean = false
)

