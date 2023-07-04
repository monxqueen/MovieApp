package com.monique.projetointegrador.domain.repository

import com.monique.projetointegrador.domain.model.Movie
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesRepository {
    fun addToFavorites(movie: Movie): Flow<List<Movie>>
    fun removeFromFavorites(movie: Movie): Flow<List<Movie>>
    fun getFavoriteMovies(): Flow<List<Movie>>
}