package com.monique.projetointegrador.domain.repository

import android.net.Uri
import com.monique.projetointegrador.domain.model.*
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(): Flow<List<Movie>>
    fun getMovieDetails(movieId: Int): Flow<MovieDetail>
    fun getAllGenres(): Flow<List<Genre>>
    fun getCast(movieId: Int): Flow<List<Cast>>
    fun getMoviesByGenre(genresId: String): Flow<List<Movie>>
    fun getCertification(movieId: Int): Flow<List<Certification>?>
    fun searchForMovie(movieSearched: Uri): Flow<List<Movie>>
}