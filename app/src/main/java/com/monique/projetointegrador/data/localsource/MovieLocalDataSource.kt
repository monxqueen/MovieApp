package com.monique.projetointegrador.data.localsource

import com.monique.projetointegrador.data.model.movies.MovieResponse
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun addToFavorites(movie: MovieResponse): Flow<List<MovieResponse>>
    fun removeFromFavorites(movie: MovieResponse): Flow<List<MovieResponse>>
    fun getFavoriteMovies(): Flow<List<MovieResponse>>
}