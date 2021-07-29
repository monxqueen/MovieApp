package com.monique.projetointegrador.presentation

import com.monique.projetointegrador.domain.Movie

interface MovieListener {
    fun openMovieDetails(movieId: Int)
    fun loadMoviesWithGenre(genreIds: List<Int>)
    //fun saveMoviesToFavoriteTab(movie: Movie, addOrRemove: String)
    fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean)
}