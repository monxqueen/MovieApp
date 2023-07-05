package com.monique.projetointegrador.features.popularmovies.presentation

import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.domain.model.Movie

data class PopularMoviesState(
    val onPopularMoviesReceived: List<Movie> = emptyList(),
    val onGeneralError: Boolean = false,
    val onGenresReceived: List<Genre> = emptyList()
) {
    fun isGeneralError(isError: Boolean) = copy(onGeneralError = isError)
}