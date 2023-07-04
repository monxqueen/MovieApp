package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.model.Genre
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(private val repository: MoviesRepository) {
    fun executeGenres(): Flow<List<Genre>> = repository.getAllGenres()
}