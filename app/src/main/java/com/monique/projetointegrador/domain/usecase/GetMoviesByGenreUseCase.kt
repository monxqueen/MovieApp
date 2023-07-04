package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetMoviesByGenreUseCase(private val repository: MoviesRepository) {
    fun executeMoviesByGenre(genresId: String): Flow<List<Movie>> =
        repository.getMoviesByGenre(genresId)
}