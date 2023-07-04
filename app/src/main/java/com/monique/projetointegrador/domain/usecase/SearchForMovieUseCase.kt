package com.monique.projetointegrador.domain.usecase

import android.net.Uri
import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class SearchForMovieUseCase(private val moviesRepository: MoviesRepository) {
    fun executeSearch(movieSearched: Uri): Flow<List<Movie>> =
        moviesRepository.searchForMovie(movieSearched)
}