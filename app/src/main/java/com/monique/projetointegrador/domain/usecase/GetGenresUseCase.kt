package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.data.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl

class GetGenresUseCase(private val repository: MoviesRepositoryImpl = MoviesRepositoryImpl()) {
    fun executeGenres() = repository.getAllGenres()
}