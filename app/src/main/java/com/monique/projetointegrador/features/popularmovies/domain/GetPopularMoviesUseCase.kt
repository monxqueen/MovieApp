package com.monique.projetointegrador.features.popularmovies.domain

import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.popularmovies.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(private val moviesRepository: PopularMoviesRepository) {
    fun execute() : Flow<List<Movie>> = moviesRepository.getPopularMovies()
}