package com.monique.projetointegrador.data.remotesource


import com.monique.projetointegrador.data.model.genres.GenresListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenresRemoteDataSourceImpl(private val genresApi: GenresApi) : GenresRemoteDataSource {
    override fun getAllGenres(): Flow<GenresListResponse> {
        return flow { emit(genresApi.getAllGenres()) }
    }
}