package com.monique.projetointegrador.presentation.application

import android.app.Application
import com.monique.projetointegrador.data.localsource.database.AppDatabaseProvider

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabaseProvider.initDatabase(applicationContext)
    }
}