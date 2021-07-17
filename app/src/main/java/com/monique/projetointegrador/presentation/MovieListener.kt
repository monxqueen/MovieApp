package com.monique.projetointegrador.presentation

import com.monique.projetointegrador.data.model.Movies

interface MovieListener {
    fun openMovieDetails(movie: Movies)
    fun loadMoviesWithGenre(genreIds: List<Int>)
}