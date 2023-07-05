package com.monique.projetointegrador.application

import android.app.Application
import com.monique.projetointegrador.data.localsource.database.AppDatabaseProvider
import com.monique.projetointegrador.di.Modules
import com.monique.projetointegrador.features.favoritemovies.di.FavoriteMovieModule
import com.monique.projetointegrador.features.moviedetails.di.MovieDetailsModule
import com.monique.projetointegrador.features.moviesearch.di.MovieSearchModule
import com.monique.projetointegrador.features.popularmovies.di.PopularMoviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabaseProvider.initDatabase(applicationContext)

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)

            Modules().loadModules()
            FavoriteMovieModule().loadModules()
            MovieDetailsModule().loadModules()
            PopularMoviesModule().loadModules()
            MovieSearchModule().loadModules()
        }
    }
}