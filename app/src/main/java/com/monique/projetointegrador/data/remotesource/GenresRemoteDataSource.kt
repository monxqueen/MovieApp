package com.monique.projetointegrador.data.remotesource

import com.monique.projetointegrador.data.model.genres.GenreResponse
import com.monique.projetointegrador.data.model.genres.GenresListResponse
import kotlinx.coroutines.flow.Flow

interface GenresRemoteDataSource {
    fun getAllGenres(): Flow<GenresListResponse>
}