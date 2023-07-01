package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl

class GetMoviesByGenreUseCase(private val repository: MoviesRepository) {
    fun executeMoviesByGenre(genresId: String) = repository.getMoviesByGenre(genresId)
}