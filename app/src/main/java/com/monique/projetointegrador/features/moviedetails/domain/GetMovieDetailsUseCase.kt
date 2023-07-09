package com.monique.projetointegrador.features.moviedetails.domain

import com.monique.projetointegrador.features.moviedetails.domain.model.Cast
import com.monique.projetointegrador.features.moviedetails.domain.model.Certification
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

 interface GetMovieDetailsUseCase {
    fun executeMovie(movieId: Int): Flow<MovieDetail>
    fun executeCast(movieId: Int): Flow<List<Cast>>
    fun executeCertification(movieId: Int): Flow<List<Certification>?>
}