package com.monique.projetointegrador.features.popularmovies.di

import com.monique.projetointegrador.features.popularmovies.data.mapper.PopularMovieMapper
import com.monique.projetointegrador.features.popularmovies.data.remotesource.PopularMoviesRemoteSource
import com.monique.projetointegrador.features.popularmovies.data.repository.PopularMoviesRepositoryImpl
import com.monique.projetointegrador.features.popularmovies.domain.GetMoviesByGenreUseCase
import com.monique.projetointegrador.features.popularmovies.domain.GetPopularMoviesUseCase
import com.monique.projetointegrador.features.popularmovies.domain.repository.PopularMoviesRepository
import com.monique.projetointegrador.features.popularmovies.presentation.PopularMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

class PopularMoviesModule {
    private val dataModule = module {
        factory<PopularMoviesRepository> {
            PopularMoviesRepositoryImpl(
                    movieLocalDataSource = get(),
                    moviesRemoteSource = get<Retrofit>().create(PopularMoviesRemoteSource::class.java),
                    movieMapper = PopularMovieMapper()
            )
        }
    }

    private val domainModule = module {
        factory { GetMoviesByGenreUseCase(repository = get()) }
        factory { GetPopularMoviesUseCase(moviesRepository = get()) }
    }

    private val presentationModule = module {
        viewModel { PopularMoviesViewModel(
            getPopularMoviesUseCase = get(),
            getMoviesByGenreUseCase = get(),
            getGenresUseCase = get(),
            favoriteMoviesUseCase = get()
        ) }
    }

    fun loadModules() = loadKoinModules(listOf(presentationModule, domainModule, dataModule))
}