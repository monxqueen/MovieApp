package com.monique.projetointegrador.data.localsource

import com.monique.projetointegrador.data.model.movies.MovieResponse
import com.monique.projetointegrador.domain.Movie
import io.reactivex.Single
import java.lang.IllegalStateException

object MovieLocalDataSourceImpl: MovieLocalDataSource{

    private val favoriteMoviesList = mutableListOf<MovieResponse>()

    /*override fun favoriteMovie(movie: MovieResponse): Single<Boolean> {
        return Single.create { emitter ->
            val result = favoriteMoviesList.add(movie) //result is either true or false
            if (result) {
                emitter.onSuccess(true)
            } else {
                emitter.onError(IllegalStateException())
            }
        }
    }*/

    override fun favoriteMovie(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            val result = favoriteMoviesList.add(movie) //result is either true or false
            if (result) {
                emitter.onSuccess(favoriteMoviesList)
            } else {
                emitter.onError(IllegalStateException())
            }
        }
    }

    override fun unfavoriteMovie(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            val movieToRemove = favoriteMoviesList.find {
                it.id == movie.id
            }
            val result = favoriteMoviesList.remove(movieToRemove)
            if (result) {
                emitter.onSuccess(favoriteMoviesList)
            } else {
                emitter.onError(IllegalStateException())
            }
        }
    }

    override fun getFavoriteMovies(): Single<List<MovieResponse>> {
        return Single.create { emitter ->
           emitter.onSuccess(favoriteMoviesList)
        }
    }

    override fun checkIfFavorite(movie: MovieResponse): Single<Boolean> {
        return Single.create { emitter ->
            val result = favoriteMoviesList.contains(movie) //result is either true or false
            if (result) {
                emitter.onSuccess(true)
            } else {
                emitter.onSuccess(false)
            }
        }
    }

}