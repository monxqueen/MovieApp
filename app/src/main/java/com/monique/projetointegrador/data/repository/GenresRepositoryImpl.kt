package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.mappers.*
import com.monique.projetointegrador.data.remotesource.GenresApi
import com.monique.projetointegrador.data.remotesource.GenresRemoteDataSource
import com.monique.projetointegrador.domain.model.*
import com.monique.projetointegrador.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GenresRepositoryImpl(
    private val genreMapper: GenreMapper,
    private val genresRemoteDataSource: GenresRemoteDataSource
): GenresRepository {

    override fun getAllGenres(): Flow<List<Genre>> {
        return genresRemoteDataSource.getAllGenres().map {
            genreMapper.map(it.genres)
        }
    }

}