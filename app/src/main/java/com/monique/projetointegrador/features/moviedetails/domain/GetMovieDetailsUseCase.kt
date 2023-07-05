package com.monique.projetointegrador.features.moviedetails.domain

import com.monique.projetointegrador.features.moviedetails.domain.model.Cast
import com.monique.projetointegrador.features.moviedetails.domain.model.Certification
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import com.monique.projetointegrador.features.moviedetails.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val movieRepository: MovieDetailsRepository) {
    fun executeMovie(movieId: Int): Flow<MovieDetail> = movieRepository.getMovieDetails(movieId)
    fun executeCast(movieId: Int): Flow<List<Cast>> = movieRepository.getCast(movieId)
    fun executeCertification(movieId: Int): Flow<List<Certification>?> =
        movieRepository.getCertification(movieId)
}