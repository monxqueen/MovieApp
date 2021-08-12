package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.localsource.database.MovieData
import com.monique.projetointegrador.data.model.movies.MovieResponse
import com.monique.projetointegrador.domain.model.Movie

class MovieDataMapper {
    fun map(movie: MovieResponse): MovieData{
            return MovieData(
                imgHome = movie.imgHome,
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                genreIds = movie.genreIds.joinToString(),
                isFavorite = movie.isFavorite
            )
    }
}