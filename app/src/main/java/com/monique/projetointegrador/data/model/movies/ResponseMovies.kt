package com.monique.projetointegrador.data.model.movies

import com.google.gson.annotations.SerializedName
import com.monique.projetointegrador.data.model.movies.MovieResponse

data class ResponseMovies(
    @SerializedName("results")
    val movieResults: List<MovieResponse>
)

/*data class MovieResponse(
    @SerializedName("poster_path")
    val img: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val rating: String? = null,
    @SerializedName("genres")
    val genres: List<Genres>? = null,
    @SerializedName("release")
    val release: Int? = null,
    @SerializedName("overview")
    val overview: String? = null
)*/
