package com.example.rickandmortysearch.features.search.presentationm

import com.example.rickandmortysearch.features.search.domain.model.AppCharacter
import kotlinx.collections.immutable.toPersistentList

fun AppCharacter.toCharacter() = UICharacter(
    id = id,
    image = image,
    name = name,
    details = buildDetails()
)

private fun AppCharacter.buildDetails() = buildList {
    add(CharacterDetail("Species", species))
    add(CharacterDetail("Status", status))
    add(CharacterDetail("Origin", origin))
    add(CharacterDetail("Created", created))

    if (type.isNotEmpty())
        add(CharacterDetail("Type", type))
}.toPersistentList()