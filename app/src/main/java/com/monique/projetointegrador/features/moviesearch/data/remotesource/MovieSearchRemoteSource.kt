package com.monique.projetointegrador.features.moviesearch.data.remotesource

import android.net.Uri
import com.monique.projetointegrador.data.model.movies.MoviesListResponse
import com.monique.projetointegrador.features.moviesearch.data.model.MovieSearchListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchRemoteSource {
    @GET("search/movie")
    suspend fun searchForMovie(@Query("query") movieSearched: Uri): MovieSearchListResponse
}