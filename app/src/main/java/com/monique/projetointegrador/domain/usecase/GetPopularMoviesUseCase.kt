package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {
    fun execute() = moviesRepository.getPopularMovies()
}