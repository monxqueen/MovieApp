package com.monique.projetointegrador.features.favoritemovies.domain

import com.monique.projetointegrador.features.favoritemovies.domain.repository.FavoriteMoviesRepository
import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class FavoriteMoviesUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepository) {

    fun getFavoriteMovies(): Flow<List<Movie>> = favoriteMoviesRepository.getFavoriteMovies()
    fun addFavoriteMovie(movie: Movie): Flow<List<Movie>> =
        favoriteMoviesRepository.addToFavorites(movie)
    fun removeFavoriteMovie(movie: Movie): Flow<List<Movie>> =
        favoriteMoviesRepository.removeFromFavorites(movie)
}