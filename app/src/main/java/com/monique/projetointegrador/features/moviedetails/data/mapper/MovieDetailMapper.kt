package com.monique.projetointegrador.features.moviedetails.data.mapper

import com.monique.projetointegrador.features.moviedetails.data.model.MovieDetailResponse
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail

internal class MovieDetailMapper {
    fun map(movieResponse: MovieDetailResponse): MovieDetail {
        return MovieDetail(
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
    }
}