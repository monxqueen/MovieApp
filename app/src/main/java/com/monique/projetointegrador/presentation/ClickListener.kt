package com.monique.projetointegrador.presentation

import com.monique.projetointegrador.domain.model.Movie

internal interface ClickListener {
    fun openMovieDetails(movieId: Int)
    fun loadMoviesWithGenre(genreIds: List<Int>)
    fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean)
}