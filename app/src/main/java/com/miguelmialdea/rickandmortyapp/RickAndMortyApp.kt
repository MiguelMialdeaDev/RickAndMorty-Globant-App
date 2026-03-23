package com.miguelmialdea.rickandmortyapp

import android.app.Application
import com.miguelmialdea.data.di.dataModule
import com.miguelmialdea.domain.di.domainModule
import com.miguelmialdea.rickandmortyapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@RickAndMortyApp)
            modules(
                dataModule,
                domainModule,
                appModule
            )
        }
    }
}