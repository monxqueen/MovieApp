package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.base.Network
import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.localsource.MovieLocalDataSourceImpl
import com.monique.projetointegrador.data.mappers.CastMapper
import com.monique.projetointegrador.data.mappers.GenreMapper
import com.monique.projetointegrador.data.mappers.MovieDetailMapper
import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.remotesource.MoviesRemoteSource
import com.monique.projetointegrador.domain.Cast
import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.domain.MovieDetail
import io.reactivex.Single

class MoviesRepositoryImpl: MoviesRepository {
    private val moviesRemoteSource: MoviesRemoteSource = Network.getMoviesRemoteSource()
    private val movieLocalDataSource = MovieLocalDataSourceImpl
    private val movieMapper = MovieMapper()
    private val genreMapper = GenreMapper()
    private val castMapper = CastMapper()
    private val movieDetailMapper = MovieDetailMapper()

    override fun getPopularMovies(): Single<List<Movie>> {
        return moviesRemoteSource
            .getPopularMovies()
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }

    override fun getMovieDetails(movieId: Int): Single<MovieDetail> {
        return moviesRemoteSource
            .getMovieDetails(movieId)
            .flatMap { movieResponse ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        val result = favoriteMovieList.any { favoriteMovie ->
                            favoriteMovie.id == movieResponse.id
                        }
                        movieResponse.isFavorite = result
                        movieResponse
                    }
            }
            .map{
                movieDetailMapper.map(it)
        }
    }

    override fun getAllGenres(): Single<List<Genre>> {
        return moviesRemoteSource
            .getAllGenres()
            .map {
                genreMapper.map(it.genres)
            }
    }

    override fun getMoviesByGenre(genresId: String): Single<List<Movie>> {
        return moviesRemoteSource
            .getMoviesByGenre(genresId)
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }

    override fun getCast(movieId: Int): Single<List<Cast>> {
        return moviesRemoteSource
            .getCast(movieId)
            .map {
                castMapper.map(it.cast)
            }
    }

}