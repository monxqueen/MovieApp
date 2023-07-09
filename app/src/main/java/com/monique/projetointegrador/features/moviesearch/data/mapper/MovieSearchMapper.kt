package com.monique.projetointegrador.features.moviesearch.data.mapper

import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.moviesearch.data.model.MovieSearchResponse

internal class MovieSearchMapper {

    fun map(movieResponseList: List<MovieSearchResponse>): List<Movie> {
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