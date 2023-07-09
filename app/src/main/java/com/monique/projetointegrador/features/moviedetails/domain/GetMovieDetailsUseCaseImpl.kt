package com.monique.projetointegrador.features.moviedetails.domain

import com.monique.projetointegrador.features.moviedetails.domain.model.Cast
import com.monique.projetointegrador.features.moviedetails.domain.model.Certification
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import com.monique.projetointegrador.features.moviedetails.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

internal class GetMovieDetailsUseCaseImpl(
    private val movieRepository: MovieDetailsRepository
) : GetMovieDetailsUseCase {
    override fun executeMovie(movieId: Int): Flow<MovieDetail> =
        movieRepository.getMovieDetails(movieId)
    override fun executeCast(movieId: Int): Flow<List<Cast>> = movieRepository.getCast(movieId)
    override fun executeCertification(movieId: Int): Flow<List<Certification>?> =
        movieRepository.getCertification(movieId)
}