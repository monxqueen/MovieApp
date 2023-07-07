package com.monique.projetointegrador.data.localsource.database

import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.mappers.MovieDataMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.data.model.movies.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieLocalDataSourceImpl(
    private val movieDataMapper: MovieDataMapper,
    private val movieResponseMapper: MovieResponseMapper
): MovieLocalDataSource {
    private val dao = AppDatabaseProvider.getFavoriteMovieDao()

    override fun addToFavorites(movie: MovieResponse): Flow<List<MovieResponse>> = flow {

        emit(
        dao?.let {
                val mappedMovie = movieDataMapper.map(movie)
                dao.insert(mappedMovie)
                val favoriteMovies = dao.getAll()
                favoriteMovies.map()
            } ?: emptyList()
        )
    }

    override fun removeFromFavorites(movie: MovieResponse): Flow<List<MovieResponse>> = flow {
        emit(
            dao?.let {
                val mappedMovie = movieDataMapper.map(movie)
                dao.delete(mappedMovie)
                val favoriteMovies = dao.getAll()
                favoriteMovies.map()
            } ?: emptyList()
        )
    }

    override fun getFavoriteMovies(): Flow<List<MovieResponse>> = flow {
        emit(
            dao?.let {
                val favoriteMovies = dao.getAll()
                favoriteMovies.map()
            } ?: emptyList()
        )
    }

    private fun List<MovieData>.map(): List<MovieResponse> {
        return this.map { movie ->
            movieResponseMapper.map(movie)
        }
    }

}