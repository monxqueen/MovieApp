package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.model.genres.GenreResponse
import com.monique.projetointegrador.domain.model.Genre

class GenreMapper {
    fun map(genresResponseList: List<GenreResponse>): List<Genre> {
        val genres = mutableListOf<Genre>()
        genresResponseList.forEach {
            val genre = Genre(
                id = it.id,
                name = it.genreName
            )
            genres.add(genre)
        }
        return genres
    }
}