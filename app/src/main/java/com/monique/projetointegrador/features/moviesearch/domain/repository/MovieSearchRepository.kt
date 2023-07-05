package com.monique.projetointegrador.features.moviesearch.domain.repository

import android.net.Uri
import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun searchForMovie(movieSearched: Uri): Flow<List<Movie>>
}