package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.GenresRepository
import com.monique.projetointegrador.domain.model.Genre
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(private val repository: GenresRepository) {
    fun executeGenres(): Flow<List<Genre>> = repository.getAllGenres()
}