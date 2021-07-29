package com.monique.projetointegrador.data.model.movies

import com.google.gson.annotations.SerializedName
import com.monique.projetointegrador.data.model.movies.MovieResponse

data class ResponseMovies(
    @SerializedName("results")
    val movieResults: List<MovieResponse>
)
