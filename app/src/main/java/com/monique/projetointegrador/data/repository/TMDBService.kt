package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.model.Movies
import retrofit2.http.GET

interface TMDBService {
    @GET("movie/{movie_id}")
    fun getMovieDetails(): Movies

    /* @GET("movie/popular")
    fun getPopularMovie(): */
}