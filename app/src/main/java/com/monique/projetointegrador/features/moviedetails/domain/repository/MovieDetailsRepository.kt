package com.monique.projetointegrador.features.moviedetails.domain.repository

import com.monique.projetointegrador.features.moviedetails.domain.model.Cast
import com.monique.projetointegrador.features.moviedetails.domain.model.Certification
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Int): Flow<MovieDetail>
    fun getCertification(movieId: Int): Flow<List<Certification>?>
    fun getCast(movieId: Int): Flow<List<Cast>>
}