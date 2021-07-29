package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.data.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.Movie
import io.reactivex.Single

class GetAllMoviesUseCase(private val moviesRepository: MoviesRepositoryImpl = MoviesRepositoryImpl()) {
    fun execute() = moviesRepository.getPopularMovies()
}