package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.localsource.database.MovieLocalDataSourceImpl
import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.domain.repository.FavoriteMoviesRepository
import io.reactivex.Single

class FavoriteMoviesRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieMapper: MovieMapper,
    private val movieResponseMapper: MovieResponseMapper
): FavoriteMoviesRepository {


    override fun addToFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .addToFavorites(movieMapped)
            .map{
                movieMapper.map(it)
            }
    }

    override fun removeFromFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .removeFromFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }

    override fun getFavoriteMovies(): Single<List<Movie>> {
        return movieLocalDataSource
            .getFavoriteMovies()
            .map {
                movieMapper.map(it)
            }
    }
}