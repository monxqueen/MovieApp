package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl

class GetGenresUseCase(private val repository: MoviesRepository = MoviesRepositoryImpl()) {
    fun executeGenres() = repository.getAllGenres()
}