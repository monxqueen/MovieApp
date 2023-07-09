package com.monique.projetointegrador.features.popularmovies.domain

import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesByGenreUseCase {
    fun executeMoviesByGenre(genresId: String): Flow<List<Movie>>
}