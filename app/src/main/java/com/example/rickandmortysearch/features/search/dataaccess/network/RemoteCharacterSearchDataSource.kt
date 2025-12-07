package com.example.rickandmortysearch.features.search.dataaccess.network

import com.example.rickandmortysearch.features.search.dataaccess.CharacterDataSource
import com.example.rickandmortysearch.features.search.dataaccess.EmptySearchResult
import com.example.rickandmortysearch.features.search.dataaccess.SearchResult
import com.example.rickandmortysearch.features.search.dataaccess.SuccessSearchResult

class RemoteCharacterSearchDataSource(
    private val service: CharacterSearchService
): CharacterDataSource {
    override suspend fun getCharacters(name: String): SearchResult {
        val response = service.searchCharacters(name)
        if (!response.isSuccessful) {
            return EmptySearchResult
        }

        return  when(val body = response.body()) {
            null -> EmptySearchResult
            else -> SuccessSearchResult(body)
        }
    }
}