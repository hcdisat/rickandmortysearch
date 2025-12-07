package com.example.rickandmortysearch.features.search.domain.model

data class AppCharacter(
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val url: String,
    val created: String,
    val origin: String,
)

sealed interface CharacterSearchResult {
    data class Success(val characters: List<AppCharacter>) : CharacterSearchResult
    object Empty : CharacterSearchResult
}