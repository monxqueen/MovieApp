package com.monique.projetointegrador.features.popularmovies.domain

import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {
    fun execute(): Flow<List<Movie>>
}