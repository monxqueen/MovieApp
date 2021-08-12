package com.monique.projetointegrador.data.localsource

import com.monique.projetointegrador.data.model.movies.MovieResponse
import io.reactivex.Single

interface MovieLocalDataSource {
    fun addToFavorites(movie: MovieResponse): Single<List<MovieResponse>>
    fun removeFromFavorites(movie: MovieResponse): Single<List<MovieResponse>>
    fun getFavoriteMovies(): Single<List<MovieResponse>>
}