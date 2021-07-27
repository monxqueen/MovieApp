package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.base.Network
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
    private val movieMapper = MovieMapper()
    private val genreMapper = GenreMapper()
    private val castMapper = CastMapper()
    private val movieDetailMapper = MovieDetailMapper()

    override fun getPopularMovies(): Single<List<Movie>> {
        return moviesRemoteSource
            .getPopularMovies()
            .map {
                movieMapper.map(it.movieResults)
            }
    }

    override fun getMovieDetails(movieId: Int): Single<MovieDetail> {
        return moviesRemoteSource
            .getMovieDetails(movieId)
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
            .map {
                movieMapper.map(it.movieResults)
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