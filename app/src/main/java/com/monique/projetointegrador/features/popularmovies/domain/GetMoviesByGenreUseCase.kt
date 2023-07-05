package com.monique.projetointegrador.features.popularmovies.domain

import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.popularmovies.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByGenreUseCase(private val repository: PopularMoviesRepository) {
    fun executeMoviesByGenre(genresId: String): Flow<List<Movie>> =
        repository.getMoviesByGenre(genresId)
}