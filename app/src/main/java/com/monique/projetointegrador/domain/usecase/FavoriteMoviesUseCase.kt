package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.data.repository.FavoriteMoviesRepositoryImpl
import com.monique.projetointegrador.domain.Movie

class FavoriteMoviesUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepositoryImpl = FavoriteMoviesRepositoryImpl()) {

    fun getFavoriteMovies() = favoriteMoviesRepository.getFavoriteMovies()
    fun addFavoriteMovie(movie: Movie) = favoriteMoviesRepository.favoriteMovie(movie)
    fun removeFavoriteMovie(movie: Movie) = favoriteMoviesRepository.unfavoriteMovie(movie)
    fun isFavorite(movie: Movie) = favoriteMoviesRepository.checkIfFavorite(movie)
    //devo criar um usecase diferente pra cada uma dessas funções?
}