package com.monique.projetointegrador.features.popularmovies.data.repository

import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.remotesource.MoviesRemoteSource
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.popularmovies.data.mapper.PopularMovieMapper
import com.monique.projetointegrador.features.popularmovies.data.remotesource.PopularMoviesRemoteSource
import com.monique.projetointegrador.features.popularmovies.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PopularMoviesRepositoryImpl(
    private val movieMapper: PopularMovieMapper,
    private val moviesRemoteSource: PopularMoviesRemoteSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : PopularMoviesRepository {
    override fun getPopularMovies(): Flow<List<Movie>> {
        return flow {
            emit(
                movieMapper.map(
                    moviesRemoteSource
                        .getPopularMovies()
                        .movieResults
                        .map { movieResponse ->
                            movieLocalDataSource
                                .getFavoriteMovies()
                                .map { favoriteMovieList ->
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

    override fun getMoviesByGenre(genresId: String): Flow<List<Movie>> {
        return flow {
            emit(
                movieMapper.map(
                    moviesRemoteSource
                        .getMoviesByGenre(genresId)
                        .movieResults
                        .map { movieResponse ->
                            movieLocalDataSource
                                .getFavoriteMovies()
                                .map { favoriteMovieList ->
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