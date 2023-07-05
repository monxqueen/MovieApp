package com.monique.projetointegrador.features.favoritemovies.di

import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.features.favoritemovies.data.repository.FavoriteMoviesRepositoryImpl
import com.monique.projetointegrador.features.favoritemovies.domain.FavoriteMoviesUseCase
import com.monique.projetointegrador.features.favoritemovies.domain.repository.FavoriteMoviesRepository
import com.monique.projetointegrador.features.favoritemovies.presentation.FavoriteMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class FavoriteMovieModule {
    private val dataModule = module {
        factory<FavoriteMoviesRepository> {
            FavoriteMoviesRepositoryImpl(
                movieLocalDataSource = get(),
                movieMapper = MovieMapper(),
                movieResponseMapper = MovieResponseMapper()
            )
        }
    }

    private val domainModule = module {
        factory { FavoriteMoviesUseCase(favoriteMoviesRepository = get()) }
    }

    private val presentationModule = module {
        viewModel {
            FavoriteMoviesViewModel(
                getGenresUseCase = get(),
                favoriteMoviesUseCase = get()
            )
        }
    }

    fun loadModules() = loadKoinModules(listOf(dataModule, domainModule, presentationModule))
}
