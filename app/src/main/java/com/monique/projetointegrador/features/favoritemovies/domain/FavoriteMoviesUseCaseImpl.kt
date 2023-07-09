package com.monique.projetointegrador.features.favoritemovies.domain

import com.monique.projetointegrador.features.favoritemovies.domain.repository.FavoriteMoviesRepository
import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

internal class FavoriteMoviesUseCaseImpl(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) : FavoriteMoviesUseCase {

    override fun getFavoriteMovies(): Flow<List<Movie>> = favoriteMoviesRepository.getFavoriteMovies()
    override fun addFavoriteMovie(movie: Movie): Flow<List<Movie>> =
        favoriteMoviesRepository.addToFavorites(movie)
    override fun removeFavoriteMovie(movie: Movie): Flow<List<Movie>> =
        favoriteMoviesRepository.removeFromFavorites(movie)
}