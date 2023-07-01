package com.monique.projetointegrador.presentation.application

import android.app.Application
import com.monique.projetointegrador.data.localsource.database.AppDatabaseProvider
import com.monique.projetointegrador.di.dataModules
import com.monique.projetointegrador.di.domainModules
import com.monique.projetointegrador.di.presentationModules
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
            modules(listOf(dataModules, domainModules, presentationModules))
        }
    }
}