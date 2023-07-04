package com.monique.projetointegrador.data.repository

import android.net.Uri
import com.monique.projetointegrador.data.base.Network
import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.mappers.*
import com.monique.projetointegrador.data.remotesource.MoviesRemoteSource
import com.monique.projetointegrador.domain.model.*
import com.monique.projetointegrador.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieMapper: MovieMapper,
    private val genreMapper: GenreMapper,
    private val castMapper: CastMapper,
    private val movieDetailMapper: MovieDetailMapper,
    private val certificationMapper: CertificationMapper,
): MoviesRepository {
    private val moviesRemoteSource: MoviesRemoteSource = Network.getMoviesRemoteSource()

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

    override fun getMovieDetails(movieId: Int): Flow<MovieDetail> {
        return flow {
            emit(
                movieDetailMapper.map(
                    moviesRemoteSource
                        .getMovieDetails(movieId).also { movieResponse ->
                            movieLocalDataSource
                                .getFavoriteMovies()
                                .map {favoriteMovieList ->
                                    val result = favoriteMovieList.any { favoriteMovie ->
                                        favoriteMovie.id == movieResponse.id
                                    }
                                    movieResponse.isFavorite = result
                                    movieResponse
                                }
                        }
                )
            )
        }
    }

    override fun getAllGenres(): Flow<List<Genre>> {
        return flow {
            emit(
                genreMapper.map(
                    moviesRemoteSource.getAllGenres().genres
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

    override fun getCast(movieId: Int): Flow<List<Cast>> {
        return flow {
            emit(castMapper.map(moviesRemoteSource.getCast(movieId).cast))
        }
    }

    override fun getCertification(movieId: Int): Flow<List<Certification>?> {
        return flow {
            emit(
                certificationMapper.map(
                    moviesRemoteSource.getCertification(movieId).results.find { certificationResponse ->
                        certificationResponse.region == BR
                    }?.releaseDates
                )
            )
        }
    }

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

    companion object {
        private const val BR = "BR"
    }

}