package com.example.rickandmortysearch

import android.app.Application
import com.example.rickandmortysearch.di.concurrentModule
import com.example.rickandmortysearch.features.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(
                searchModule,
                concurrentModule,
            )
        }
    }
}