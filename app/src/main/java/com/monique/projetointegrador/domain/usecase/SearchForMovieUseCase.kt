package com.monique.projetointegrador.domain.usecase

import android.net.Uri
import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl

class SearchForMovieUseCase(private val moviesRepository: MoviesRepository) {
    fun executeSearch(movieSearched: Uri) = moviesRepository.searchForMovie(movieSearched)
}