package com.monique.projetointegrador.features.favoritemovies.data.repository

import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.favoritemovies.domain.repository.FavoriteMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMoviesRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieMapper: MovieMapper,
    private val movieResponseMapper: MovieResponseMapper
): FavoriteMoviesRepository {


    override fun addToFavorites(movie: Movie): Flow<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .addToFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }

    override fun removeFromFavorites(movie: Movie): Flow<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .removeFromFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieLocalDataSource
            .getFavoriteMovies()
            .map {
                movieMapper.map(it)
            }
    }
}