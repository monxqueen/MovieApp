package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.GenresRepository
import com.monique.projetointegrador.domain.model.Genre
import kotlinx.coroutines.flow.Flow

internal class GetGenresUseCaseImpl(private val repository: GenresRepository) : GetGenresUseCase {
    override fun executeGenres(): Flow<List<Genre>> = repository.getAllGenres()
}