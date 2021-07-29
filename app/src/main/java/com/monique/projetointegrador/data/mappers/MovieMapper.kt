package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.model.movies.MovieResponse
import com.monique.projetointegrador.domain.Movie

class MovieMapper {
    fun map(movieResponseList: List<MovieResponse>): List<Movie> {
        val movies = mutableListOf<Movie>()
        movieResponseList.forEach {
            val movie = Movie(
                imgHome = it.imgHome,
                id = it.id,
                title = it.title,
                rating = it.rating,
                genreIds = it.genreIds,
            )
            movies.add(movie)
        }
        return movies
    }
}