package com.monique.projetointegrador.features.moviesearch.domain

import android.net.Uri
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.moviesearch.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow

class SearchForMovieUseCase(private val moviesRepository: MovieSearchRepository) {
    fun executeSearch(movieSearched: Uri): Flow<List<Movie>> =
        moviesRepository.searchForMovie(movieSearched)
}