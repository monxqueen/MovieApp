 package com.monique.projetointegrador.features.moviedetails.data.repository

import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.features.moviedetails.data.mapper.CastMapper
import com.monique.projetointegrador.features.moviedetails.data.mapper.CertificationMapper
import com.monique.projetointegrador.features.moviedetails.data.mapper.MovieDetailMapper
import com.monique.projetointegrador.features.moviedetails.data.model.MovieDetailResponse
import com.monique.projetointegrador.features.moviedetails.data.remotesource.MovieDetailsRemoteSource
import com.monique.projetointegrador.features.moviedetails.domain.model.Cast
import com.monique.projetointegrador.features.moviedetails.domain.model.Certification
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import com.monique.projetointegrador.features.moviedetails.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class MovieDetailsRepositoryImpl(
    private val castMapper: CastMapper,
    private val movieDetailMapper: MovieDetailMapper,
    private val certificationMapper: CertificationMapper,
    private val moviesRemoteSource: MovieDetailsRemoteSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieDetailsRepository {

    override fun getMovieDetails(movieId: Int): Flow<MovieDetail> {
        return flow {
            emit(
                movieDetailMapper.map(
                    moviesRemoteSource
                        .getMovieDetails(movieId).apply {
                            movieLocalDataSource
                                .getFavoriteMovies()
                                .collect { favoriteMovieList ->
                                    val result = favoriteMovieList.any { favoriteMovie ->
                                        favoriteMovie.id == this.id
                                    }
                                    this.isFavorite = result
                                }
                        }

                )
            )
        }
    }

    override fun getCast(movieId: Int): Flow<List<Cast>> {
        return flow {
            emit(castMapper.map(moviesRemoteSource.getCast(movieId).cast))
        }
    }

    override fun getCertification(movieId: Int): Flow<List<Certification>?> {
        return flow {
            emit(
                certificationMapper.map(
                    moviesRemoteSource.getCertification(movieId).results.find { certificationResponse ->
                        certificationResponse.region == BR
                    }?.releaseDates
                )
            )
        }
    }

    companion object {
        private const val BR = "BR"
    }
}