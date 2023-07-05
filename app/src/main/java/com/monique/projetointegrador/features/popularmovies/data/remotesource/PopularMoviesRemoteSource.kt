package com.monique.projetointegrador.features.popularmovies.data.remotesource

import com.monique.projetointegrador.data.model.movies.MoviesListResponse
import com.monique.projetointegrador.features.popularmovies.data.model.PopularMoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesRemoteSource {
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviesListResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres", encoded = true) genresId: String
    ): PopularMoviesListResponse
}