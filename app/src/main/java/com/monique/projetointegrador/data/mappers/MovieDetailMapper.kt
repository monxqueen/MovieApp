package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.model.movies.MovieDetailResponse
import com.monique.projetointegrador.domain.model.MovieDetail

class MovieDetailMapper {
    fun map(movieResponse: MovieDetailResponse): MovieDetail {
        val movieDetailed = MovieDetail(
            backdropPath = movieResponse.backdropPath,
            genres = movieResponse.genres,
            id = movieResponse.id,
            overview = movieResponse.overview,
            releaseDate = movieResponse.releaseDate,
            runtime = movieResponse.runtime,
            voteAverage = movieResponse.voteAverage,
            title = movieResponse.title,
            isFavorite = movieResponse.isFavorite
        )
        return movieDetailed
    }
}