package com.monique.projetointegrador.data.remotesource

import com.monique.projetointegrador.data.model.cast.ResponseCast
import com.monique.projetointegrador.data.model.certification.ResponseCertification
import com.monique.projetointegrador.data.model.genres.ResponseGenres
import com.monique.projetointegrador.data.model.movies.MovieDetailResponse
import com.monique.projetointegrador.data.model.movies.ResponseMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteSource {

    //Antes eu estava usando o observable, mas não é tão importante usar pra esse projeto pois ele só faz requisições q retornam response na hora
    //um observable seria mais pra caso eu fosse implementar algo tipo um chat, que sempre precisa ficar de olho se chegou uma resposta ou não
    @GET("movie/popular")
    fun getPopularMovies(): Single<ResponseMovies>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<MovieDetailResponse>

    @GET("search/movie")
    fun searchForMovie(@Query("movieSearched") query: String): Single<ResponseMovies>

    @GET("movie/{movie_id}/credits")
    fun getCast(@Path("movie_id") movieId: Int): Single<ResponseCast>

    @GET("genre/movie/list")
    fun getAllGenres(): Single<ResponseGenres>

    @GET("movie/{movie_id}/release_dates")
    fun getCertification(@Path("movie_id") movieId: Int): Single<ResponseCertification>

    @GET("discover/movie")
    fun getMoviesByGenre(@Query("with_genres", encoded = true) genresId: String): Single<ResponseMovies>
}