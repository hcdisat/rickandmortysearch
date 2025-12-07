package com.example.rickandmortysearch.features.search.domain

import com.example.rickandmortysearch.features.search.domain.model.CharacterSearchResult

interface CharacterSearchRepository {
    suspend fun searchCharacterByName(name: String): Result<CharacterSearchResult>
}