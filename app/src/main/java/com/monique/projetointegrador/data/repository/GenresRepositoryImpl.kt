package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.mappers.*
import com.monique.projetointegrador.data.remotesource.MoviesRemoteSource
import com.monique.projetointegrador.domain.model.*
import com.monique.projetointegrador.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenresRepositoryImpl(
    private val genreMapper: GenreMapper,
    private val moviesRemoteSource: MoviesRemoteSource
): GenresRepository {

    override fun getAllGenres(): Flow<List<Genre>> {
        return flow {
            emit(
                genreMapper.map(
                    moviesRemoteSource.getAllGenres().genres
                )
            )
        }
    }

}