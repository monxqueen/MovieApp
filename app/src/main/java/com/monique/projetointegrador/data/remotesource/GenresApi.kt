package com.monique.projetointegrador.data.remotesource

import com.monique.projetointegrador.data.model.genres.GenresListResponse
import retrofit2.http.GET

interface GenresApi {

    @GET("genre/movie/list")
    suspend fun getAllGenres(): GenresListResponse

}