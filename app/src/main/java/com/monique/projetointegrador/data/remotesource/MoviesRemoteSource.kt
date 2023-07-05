package com.monique.projetointegrador.data.remotesource

import android.net.Uri
import com.monique.projetointegrador.features.moviedetails.data.model.cast.CastListResponse
import com.monique.projetointegrador.features.moviedetails.data.model.certification.CertificationListReponse
import com.monique.projetointegrador.data.model.genres.GenresListResponse
import com.monique.projetointegrador.features.moviedetails.data.model.MovieDetailResponse
import com.monique.projetointegrador.data.model.movies.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteSource {

    @GET("genre/movie/list")
    suspend fun getAllGenres(): GenresListResponse

}