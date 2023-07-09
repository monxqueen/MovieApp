package com.monique.projetointegrador.features.popularmovies.domain

import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.popularmovies.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

internal class GetMoviesByGenreUseCaseImpl(
    private val repository: PopularMoviesRepository
) : GetMoviesByGenreUseCase {
    override fun executeMoviesByGenre(genresId: String): Flow<List<Movie>> =
        repository.getMoviesByGenre(genresId)
}