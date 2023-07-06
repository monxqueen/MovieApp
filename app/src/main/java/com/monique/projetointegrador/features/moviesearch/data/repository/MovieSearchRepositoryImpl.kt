package com.monique.projetointegrador.features.moviesearch.data.repository

import android.net.Uri
import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.remotesource.MoviesRemoteSource
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.moviesearch.data.mapper.MovieSearchMapper
import com.monique.projetointegrador.features.moviesearch.data.remotesource.MovieSearchRemoteSource
import com.monique.projetointegrador.features.moviesearch.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieSearchRepositoryImpl(
    private val moviesRemoteSource: MovieSearchRemoteSource,
    private val movieMapper: MovieSearchMapper,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieSearchRepository {
    override fun searchForMovie(movieSearched: Uri): Flow<List<Movie>> {
        return flow {
            emit(
                movieMapper.map(
                    moviesRemoteSource
                        .searchForMovie(movieSearched)
                        .movieResults
                        .map { movieResponse ->
                            movieLocalDataSource
                                .getFavoriteMovies()
                                .collect { favoriteMovieList ->
                                        val result = favoriteMovieList.any { favoriteMovie ->
                                            favoriteMovie.id == movieResponse.id
                                        }
                                        movieResponse.isFavorite = result
                                }
                            movieResponse
                        }
                )
            )
        }
    }
}