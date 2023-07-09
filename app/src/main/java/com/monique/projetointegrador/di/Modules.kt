package com.monique.projetointegrador.di

import com.monique.projetointegrador.data.base.RetrofitBuilder
import com.monique.projetointegrador.data.localsource.MovieLocalDataSource
import com.monique.projetointegrador.data.localsource.database.MovieLocalDataSourceImpl
import com.monique.projetointegrador.data.mappers.GenreMapper
import com.monique.projetointegrador.data.mappers.MovieDataMapper
import com.monique.projetointegrador.data.mappers.MovieMapper
import com.monique.projetointegrador.data.mappers.MovieResponseMapper
import com.monique.projetointegrador.data.remotesource.GenresApi
import com.monique.projetointegrador.data.remotesource.GenresRemoteDataSourceImpl
import com.monique.projetointegrador.data.repository.GenresRepositoryImpl
import com.monique.projetointegrador.domain.repository.GenresRepository
import com.monique.projetointegrador.domain.usecase.GetGenresUseCase
import com.monique.projetointegrador.domain.usecase.GetGenresUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

class Modules {
    private val dataModules = module {
        single { RetrofitBuilder().buildRetrofit() }
        factory { MovieDataMapper() }
        factory { MovieResponseMapper() }
        factory { MovieMapper() }
        factory { GenreMapper() }
        factory<MovieLocalDataSource> {
            MovieLocalDataSourceImpl(movieDataMapper = get(), movieResponseMapper = get())
        }
        factory<GenresRepository> {
            GenresRepositoryImpl(
                genresRemoteDataSource = GenresRemoteDataSourceImpl(
                    genresApi = get<Retrofit>().create(GenresApi::class.java)
                ),
                genreMapper = get(),
            )
        }

    }

    private val domainModules = module {
        factory<GetGenresUseCase> { GetGenresUseCaseImpl(repository = get()) }
    }

    fun loadModules() = loadKoinModules(listOf(dataModules, domainModules))
}