package com.monique.projetointegrador.data.localsource

import com.monique.projetointegrador.data.model.movies.MovieResponse
import com.monique.projetointegrador.domain.Movie
import io.reactivex.Single

interface MovieLocalDataSource {
    //fun favoriteMovie(movie: MovieResponse): Single<Boolean>
    fun favoriteMovie(movie: MovieResponse): Single<List<MovieResponse>>
    fun unfavoriteMovie(movie: MovieResponse): Single<List<MovieResponse>>
    fun getFavoriteMovies(): Single<List<MovieResponse>>
    fun checkIfFavorite(movie: MovieResponse): Single<Boolean>
}