package com.monique.projetointegrador.features.moviedetails.data.remotesource

import com.monique.projetointegrador.features.moviedetails.data.model.MovieDetailResponse
import com.monique.projetointegrador.features.moviedetails.data.model.cast.CastListResponse
import com.monique.projetointegrador.features.moviedetails.data.model.certification.CertificationListReponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MovieDetailsRemoteSource {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(@Path("movie_id") movieId: Int): CastListResponse

    @GET("movie/{movie_id}/release_dates")
    suspend fun getCertification(@Path("movie_id") movieId: Int): CertificationListReponse
}