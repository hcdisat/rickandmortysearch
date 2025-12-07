package com.example.rickandmortysearch.features.search.di

import com.example.rickandmortysearch.di.DispatcherQualifiers
import com.example.rickandmortysearch.features.search.dataaccess.CharacterDataSource
import com.example.rickandmortysearch.features.search.dataaccess.CharacterSearchRepositoryImpl
import com.example.rickandmortysearch.features.search.dataaccess.network.CharacterSearchService
import com.example.rickandmortysearch.features.search.dataaccess.network.RemoteCharacterSearchDataSource
import com.example.rickandmortysearch.features.search.domain.CharacterSearchRepository
import com.example.rickandmortysearch.features.search.domain.usecase.SearchCharacterByNameUseCase
import com.example.rickandmortysearch.features.search.domain.usecase.SearchCharacterByNameUseCaseImpl
import com.example.rickandmortysearch.features.search.presentationm.SearchCharacterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single<CharacterDataSource> { RemoteCharacterSearchDataSource(get()) }
    single<CharacterSearchRepository> { CharacterSearchRepositoryImpl(get()) }
    single<CharacterSearchService> { CharacterSearchService.create() }

    factory<SearchCharacterByNameUseCase> { SearchCharacterByNameUseCaseImpl(get()) }

    viewModel { SearchCharacterViewModel(get(DispatcherQualifiers.IO), get()) }
}