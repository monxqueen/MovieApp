package com.monique.projetointegrador.features.moviedetails.di

import com.monique.projetointegrador.features.moviedetails.data.mapper.CastMapper
import com.monique.projetointegrador.features.moviedetails.data.mapper.CertificationMapper
import com.monique.projetointegrador.features.moviedetails.data.mapper.MovieDetailMapper
import com.monique.projetointegrador.features.moviedetails.data.remotesource.MovieDetailsRemoteSource
import com.monique.projetointegrador.features.moviedetails.data.repository.MovieDetailsRepositoryImpl
import com.monique.projetointegrador.features.moviedetails.domain.GetMovieDetailsUseCaseImpl
import com.monique.projetointegrador.features.moviedetails.domain.GetMovieDetailsUseCase
import com.monique.projetointegrador.features.moviedetails.domain.repository.MovieDetailsRepository
import com.monique.projetointegrador.features.moviedetails.presentation.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

internal class MovieDetailsModule {
    private val dataModule = module {
        factory<MovieDetailsRepository> {
            MovieDetailsRepositoryImpl(
                castMapper = CastMapper(),
                movieDetailMapper = MovieDetailMapper(),
                certificationMapper = CertificationMapper(),
                moviesRemoteSource = get<Retrofit>().create(MovieDetailsRemoteSource::class.java),
                movieLocalDataSource = get()
            )
        }
    }
    private val domainModule = module {
        factory<GetMovieDetailsUseCase> { GetMovieDetailsUseCaseImpl(movieRepository = get()) }
    }
    private val presentationModule = module {
        viewModel { MovieDetailsViewModel(getMovieDetailsUseCase = get()) }
    }

    fun loadModules() = loadKoinModules(listOf(dataModule, domainModule, presentationModule))
}