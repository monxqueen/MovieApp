package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.FavoriteMoviesRepository
import com.monique.projetointegrador.data.repository.FavoriteMoviesRepositoryImpl
import com.monique.projetointegrador.domain.model.Movie

class FavoriteMoviesUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepository) {

    fun getFavoriteMovies() = favoriteMoviesRepository.getFavoriteMovies()
    fun addFavoriteMovie(movie: Movie) = favoriteMoviesRepository.addToFavorites(movie)
    fun removeFavoriteMovie(movie: Movie) = favoriteMoviesRepository.removeFromFavorites(movie)
}