package com.monique.projetointegrador.data.remotesource

import android.net.Uri
import com.monique.projetointegrador.data.model.cast.CastListResponse
import com.monique.projetointegrador.data.model.certification.CertificationListReponse
import com.monique.projetointegrador.data.model.genres.GenresListResponse
import com.monique.projetointegrador.data.model.movies.MovieDetailResponse
import com.monique.projetointegrador.data.model.movies.MoviesListResponse
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteSource {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailResponse

    @GET("search/movie")
    suspend fun searchForMovie(@Query("query") movieSearched: Uri): MoviesListResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(@Path("movie_id") movieId: Int): CastListResponse

    @GET("genre/movie/list")
    suspend fun getAllGenres(): GenresListResponse

    @GET("movie/{movie_id}/release_dates")
    suspend fun getCertification(@Path("movie_id") movieId: Int): CertificationListReponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("with_genres", encoded = true) genresId: String): MoviesListResponse
}