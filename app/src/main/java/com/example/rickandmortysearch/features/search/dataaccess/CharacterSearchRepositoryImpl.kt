package com.example.rickandmortysearch.features.search.dataaccess

import com.example.rickandmortysearch.features.search.dataaccess.model.transformer.toCharacter
import com.example.rickandmortysearch.features.search.domain.CharacterSearchRepository
import com.example.rickandmortysearch.features.search.domain.model.CharacterSearchResult

class CharacterSearchRepositoryImpl(
    private val dataSource: CharacterDataSource
): CharacterSearchRepository {
    override suspend fun searchCharacterByName(name: String): Result<CharacterSearchResult> =
        runCatching { dataSource.getCharacters(name) }
            .mapCatching { responseResult ->
                when (responseResult) {
                    EmptySearchResult -> CharacterSearchResult.Empty

                    is SuccessSearchResult -> {
                        CharacterSearchResult.Success(
                            responseResult.characters.results.map { it.toCharacter() }
                        )
                    }
                }
            }
}