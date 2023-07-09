package com.monique.projetointegrador.features.popularmovies.data.mapper

import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.popularmovies.data.model.PopularMovieResponse

internal class PopularMovieMapper {
    fun map(movieResponseList: List<PopularMovieResponse>): List<Movie> {
        val movies = mutableListOf<Movie>()
        movieResponseList.forEach {
            val movie = Movie(
                imgHome = it.imgHome,
                id = it.id,
                title = it.title,
                rating = it.rating,
                genreIds = it.genreIds,
                isFavorite = it.isFavorite
            )
            movies.add(movie)
        }
        return movies
    }
}