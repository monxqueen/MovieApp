package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.model.Cast
import com.monique.projetointegrador.domain.model.Certification
import com.monique.projetointegrador.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val movieRepository: MoviesRepository) {
    fun executeMovie(movieId: Int): Flow<MovieDetail> = movieRepository.getMovieDetails(movieId)
    fun executeCast(movieId: Int): Flow<List<Cast>> = movieRepository.getCast(movieId)
    fun executeCertification(movieId: Int): Flow<List<Certification>?> =
        movieRepository.getCertification(movieId)
}