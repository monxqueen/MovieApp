package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.localsource.database.MovieData
import com.monique.projetointegrador.data.model.movies.MovieResponse
import com.monique.projetointegrador.domain.model.Movie

class MovieResponseMapper {
    fun map(movie: Movie): MovieResponse {
            return MovieResponse(
                imgHome = movie.imgHome,
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                genreIds = movie.genreIds,
                isFavorite = movie.isFavorite
            )
    }

    fun map(movie: MovieData): MovieResponse {
        return MovieResponse(
            imgHome = movie.imgHome,
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            genreIds = movie.genreIds.asIntList(),
            isFavorite = movie.isFavorite
        )
    }

    private fun String.asIntList(): List<Int> {
        return this.split(",").map { it.trim().toInt() }
    }
}