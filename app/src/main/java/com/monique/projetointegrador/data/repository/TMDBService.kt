package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.model.Movies
import com.monique.projetointegrador.data.model.ResponseGenres
import com.monique.projetointegrador.data.model.ResponseMovies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    fun getPopularMovies(): Observable<ResponseMovies>

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movie_id: Int): Observable<Movies>

    @GET("search/movie")
    fun searchForMovie(@Query("movieSearched") query: String): Observable<ResponseMovies>

    @GET("movie/{movie_id}/credits")
    fun getCast()

    @GET("genre/movie/list")
    fun getAllGenres(@Query("pt-BR") language: String): Observable<ResponseGenres>
}