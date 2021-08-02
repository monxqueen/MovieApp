package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl

class GetMovieDetailsUseCase(private val movieRepository: MoviesRepositoryImpl = MoviesRepositoryImpl()) {
    fun executeMovie(movieId: Int) = movieRepository.getMovieDetails(movieId)
    fun executeCast(movieId: Int) = movieRepository.getCast(movieId)
    fun executeCertification(movieId: Int) = movieRepository.getCertification(movieId)
}