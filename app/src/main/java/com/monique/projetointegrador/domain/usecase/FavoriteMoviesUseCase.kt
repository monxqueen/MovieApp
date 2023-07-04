package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.FavoriteMoviesRepository
import com.monique.projetointegrador.data.repository.FavoriteMoviesRepositoryImpl
import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class FavoriteMoviesUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepository) {

    fun getFavoriteMovies(): Flow<List<Movie>> = favoriteMoviesRepository.getFavoriteMovies()
    fun addFavoriteMovie(movie: Movie): Flow<List<Movie>> =
        favoriteMoviesRepository.addToFavorites(movie)
    fun removeFavoriteMovie(movie: Movie): Flow<List<Movie>> =
        favoriteMoviesRepository.removeFromFavorites(movie)
}