package com.example.rickandmortysearch.features.search.dataaccess.model.transformer

import com.example.rickandmortysearch.features.search.dataaccess.model.ResponseCharacter
import com.example.rickandmortysearch.features.search.domain.model.AppCharacter
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun ResponseCharacter.toCharacter() = AppCharacter(
    id = id,
    image = image,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    url = url,
    origin = origin.name,
    created = created.toPresentationDate(),
)

private fun String.toPresentationDate(): String =
    runCatching {
        val instant = Instant.parse(this)
        val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy", Locale.getDefault())
        localDateTime.format(formatter)
    }.getOrDefault(this)