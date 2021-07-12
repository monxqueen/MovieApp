package com.monique.projetointegrador.presentation

import com.monique.projetointegrador.data.model.Movies

interface MovieListener {
    fun addToFavorite(element: Movies)
    fun removeFromFavorite(position: Int)
    fun elementIsFavorite(element: Movies): Boolean
    fun openMovieDetails(movie: Movies)
}