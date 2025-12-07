package com.example.rickandmortysearch.features.search.dataaccess

import com.example.rickandmortysearch.features.search.dataaccess.model.CharacterResponse

interface CharacterDataSource {
    suspend fun getCharacters(name: String): SearchResult
}

sealed interface SearchResult
data class SuccessSearchResult(val characters: CharacterResponse) : SearchResult
object EmptySearchResult : SearchResult