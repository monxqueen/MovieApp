package com.monique.projetointegrador.features.favoritemovies.domain

import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesUseCase {
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun addFavoriteMovie(movie: Movie): Flow<List<Movie>>
    fun removeFavoriteMovie(movie: Movie): Flow<List<Movie>>
}