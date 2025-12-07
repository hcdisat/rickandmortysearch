package com.example.rickandmortysearch.features.search.domain.usecase

import com.example.rickandmortysearch.features.search.domain.CharacterSearchRepository
import com.example.rickandmortysearch.features.search.domain.model.CharacterSearchResult

interface SearchCharacterByNameUseCase {
    suspend operator fun invoke(name: String): Result<CharacterSearchResult>
}

class SearchCharacterByNameUseCaseImpl(
    private val repository: CharacterSearchRepository
): SearchCharacterByNameUseCase {
    override suspend fun invoke(name: String): Result<CharacterSearchResult> =
        repository.searchCharacterByName(name)
}