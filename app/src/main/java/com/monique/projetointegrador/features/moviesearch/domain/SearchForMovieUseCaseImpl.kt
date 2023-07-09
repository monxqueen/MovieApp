package com.monique.projetointegrador.features.moviesearch.domain

import android.net.Uri
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.moviesearch.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow

internal class SearchForMovieUseCaseImpl(
    private val moviesRepository: MovieSearchRepository
) : SearchForMovieUseCase {
    override fun executeSearch(movieSearched: Uri): Flow<List<Movie>> =
        moviesRepository.searchForMovie(movieSearched)
}