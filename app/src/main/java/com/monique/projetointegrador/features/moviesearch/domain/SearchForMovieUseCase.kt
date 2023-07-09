package com.monique.projetointegrador.features.moviesearch.domain

import android.net.Uri
import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchForMovieUseCase {
    fun executeSearch(movieSearched: Uri): Flow<List<Movie>>
}