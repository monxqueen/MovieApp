package com.monique.projetointegrador.di

import com.monique.projetointegrador.data.localsource.database.MovieLocalDataSourceImpl
import com.monique.projetointegrador.data.mappers.CastMapper
import com.monique.projetointegrador.data.mappers.CertificationMapper
import com.monique.projetointegrador.data.mappers.GenreMapper
import com.monique.projetointegrador.data.mappers.MovieDataMapper
import com.monique.projetointegrador.data.mappers.MovieDetailMapper
import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.data.repository.FavoriteMoviesRepositoryImpl
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.model.Cast
import com.monique.projetointegrador.domain.repository.FavoriteMoviesRepository
import com.monique.projetointegrador.domain.repository.MoviesRepository
import com.monique.projetointegrador.domain.usecase.FavoriteMoviesUseCase
import com.monique.projetointegrador.domain.usecase.GetGenresUseCase
import com.monique.projetointegrador.domain.usecase.GetMovieDetailsUseCase
import com.monique.projetointegrador.domain.usecase.GetMoviesByGenreUseCase
import com.monique.projetointegrador.domain.usecase.GetPopularMoviesUseCase
import com.monique.projetointegrador.domain.usecase.SearchForMovieUseCase
import com.monique.projetointegrador.presentation.MoviesViewModel
import com.monique.projetointegrador.presentation.moviedetails.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModules = module {
    factory { MovieDataMapper() }
    factory { MovieResponseMapper() }
    factory { MovieMapper() }

    factory<MoviesRepository> {
        MoviesRepositoryImpl(
            movieLocalDataSource = MovieLocalDataSourceImpl(
                movieDataMapper = get(),
                movieResponseMapper = get()
            ),
            movieMapper = get(),
            genreMapper = GenreMapper(),
            castMapper = CastMapper(),
            movieDetailMapper = MovieDetailMapper(),
            certificationMapper = CertificationMapper()
        )
    }

    factory<FavoriteMoviesRepository> {
        FavoriteMoviesRepositoryImpl(
            movieLocalDataSource = MovieLocalDataSourceImpl(
                movieDataMapper = get(),
                movieResponseMapper = get()
            ),
            movieMapper = get(),
            movieResponseMapper = get()
        )
    }
}

val domainModules = module {
    factory { FavoriteMoviesUseCase(favoriteMoviesRepository = get()) }
    factory { GetGenresUseCase(repository = get()) }
    factory { GetMovieDetailsUseCase(movieRepository = get()) }
    factory { GetMoviesByGenreUseCase(repository = get()) }
    factory { GetPopularMoviesUseCase(moviesRepository = get()) }
    factory { SearchForMovieUseCase(moviesRepository = get()) }
}

val presentationModules = module {
    single {
        MoviesViewModel(
            favoriteMoviesUseCase = get(),
            getGenresUseCase = get(),
            getMoviesByGenreUseCase = get(),
            getPopularMoviesUseCase = get(),
            searchForMoviesUseCase = get()
        )
    }

    viewModel { MovieDetailsViewModel(getMovieDetailsUseCase = get()) }
}