package com.monique.projetointegrador.features.moviesearch.di

import com.monique.projetointegrador.features.moviesearch.data.mapper.MovieSearchMapper
import com.monique.projetointegrador.features.moviesearch.data.remotesource.MovieSearchRemoteSource
import com.monique.projetointegrador.features.moviesearch.data.repository.MovieSearchRepositoryImpl
import com.monique.projetointegrador.features.moviesearch.domain.SearchForMovieUseCase
import com.monique.projetointegrador.features.moviesearch.domain.SearchForMovieUseCaseImpl
import com.monique.projetointegrador.features.moviesearch.domain.repository.MovieSearchRepository
import com.monique.projetointegrador.features.moviesearch.presentation.SearchMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

internal class MovieSearchModule {
    private val dataModule = module {
        factory<MovieSearchRepository> {
            MovieSearchRepositoryImpl(
                movieMapper = MovieSearchMapper(),
                moviesRemoteSource = get<Retrofit>().create(MovieSearchRemoteSource::class.java),
                movieLocalDataSource = get()
            )
        }

    }

    private val domainModule = module {
        factory<SearchForMovieUseCase> { SearchForMovieUseCaseImpl(moviesRepository = get()) }
    }

    private val presentationModule = module {
        viewModel {
            SearchMoviesViewModel(
                favoriteMoviesUseCase = get(),
                getGenresUseCase = get(),
                searchForMoviesUseCase = get()
            )
        }
    }

    fun loadModules() = loadKoinModules(listOf(dataModule, domainModule, presentationModule))
}