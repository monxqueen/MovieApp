package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.data.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl

class GetMoviesByGenreUseCase(private val repository: MoviesRepository = MoviesRepositoryImpl()) {
    fun executeMoviesByGenre(genresId: String) = repository.getMoviesByGenre(genresId)
}