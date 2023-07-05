package com.monique.projetointegrador.features.popularmovies.domain.repository

import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<List<Movie>>
    fun getMoviesByGenre(genresId: String): Flow<List<Movie>>
}