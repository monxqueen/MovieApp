package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.model.movies.MovieResponse
import com.monique.projetointegrador.domain.Movie

class MovieResponseMapper {
    fun map(movie: Movie): MovieResponse {
            return MovieResponse(
                imgHome = movie.imgHome,
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                genreIds = movie.genreIds,
            )
    }
}