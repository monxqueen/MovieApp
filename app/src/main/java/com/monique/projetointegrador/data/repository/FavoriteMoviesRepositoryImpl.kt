package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.localsource.MovieMemoryDataSourceImpl
import com.monique.projetointegrador.data.localsource.database.MovieLocalDataSourceImpl
import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.domain.model.Movie
import io.reactivex.Single

class FavoriteMoviesRepositoryImpl: FavoriteMoviesRepository {
    private val movieLocalDataSource = MovieLocalDataSourceImpl()
    private val movieMapper = MovieMapper()
    private val movieResponseMapper = MovieResponseMapper()

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