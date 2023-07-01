package com.monique.projetointegrador.data.localsource.database

import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.localsource.MovieMemoryDataSourceImpl
import com.monique.projetointegrador.data.mappers.MovieDataMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.data.model.movies.MovieResponse
import io.reactivex.Single
import java.lang.IllegalStateException

class MovieLocalDataSourceImpl(
    private val movieDataMapper: MovieDataMapper,
    private val movieResponseMapper: MovieResponseMapper
): MovieLocalDataSource {
    private val dao = AppDatabaseProvider.getFavoriteMovieDao()

    override fun addToFavorites(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            dao?.let{
                val mappedMovie = movieDataMapper.map(movie)
                dao.insert(mappedMovie)
                val favoriteMovies = dao.getAll()
                emitter.onSuccess(favoriteMovies.map())
            } ?: emitter.onError(IllegalStateException())
        }
    }

    override fun removeFromFavorites(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            dao?.let{
                val mappedMovie = movieDataMapper.map(movie)
                dao.delete(mappedMovie)
                val favoriteMovies = dao.getAll()
                emitter.onSuccess(favoriteMovies.map())
            } ?: emitter.onError(IllegalStateException())
        }
    }

    override fun getFavoriteMovies(): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            dao?.let{
                val favoriteMovies = dao.getAll()
                emitter.onSuccess(favoriteMovies.map())
            } ?: emitter.onError(IllegalStateException())
        }
    }

    private fun List<MovieData>.map(): List<MovieResponse> {
        return this.map { movie ->
            movieResponseMapper.map(movie)
        }
    }

}