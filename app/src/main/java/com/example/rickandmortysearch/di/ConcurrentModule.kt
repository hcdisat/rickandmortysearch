package com.example.rickandmortysearch.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DispatcherQualifiers {
    val IO = named("IO")
    val MAIN = named("MAIN")
    val DEFAULT = named("DEFAULT")
}

val concurrentModule = module {
    single<CoroutineDispatcher>(DispatcherQualifiers.IO) {
        Dispatchers.IO
    }

    single<CoroutineDispatcher>(DispatcherQualifiers.MAIN) {
        Dispatchers.Main
    }

    single<CoroutineDispatcher>(DispatcherQualifiers.DEFAULT) {
        Dispatchers.Default
    }
}