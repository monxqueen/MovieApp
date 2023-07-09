package com.monique.projetointegrador.features.popularmovies.domain

import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.popularmovies.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

internal class GetPopularMoviesUseCaseImpl(
    private val moviesRepository: PopularMoviesRepository
) : GetPopularMoviesUseCase {
    override fun execute() : Flow<List<Movie>> = moviesRepository.getPopularMovies()
}