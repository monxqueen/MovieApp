package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.domain.Cast
import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.domain.MovieDetail
import io.reactivex.Single

interface MoviesRepository {
    fun getPopularMovies(): Single<List<Movie>>
    fun getMovieDetails(movieId: Int): Single<MovieDetail>
    fun getAllGenres(): Single<List<Genre>>
    fun getCast(movieId: Int): Single<List<Cast>>
    fun getMoviesByGenre(genresId: String): Single<List<Movie>>
}