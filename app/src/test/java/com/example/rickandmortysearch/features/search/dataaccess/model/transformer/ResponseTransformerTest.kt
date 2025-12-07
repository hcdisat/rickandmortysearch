package com.example.rickandmortysearch.features.search.dataaccess.model.transformer

import com.example.rickandmortysearch.utils.getCharacterData
import org.junit.Assert.*
import org.junit.Test

class ResponseTransformerTest {

    private val responseCharacter get() = getCharacterData().first()

    @Test
    fun `verify original string is returned when parsing fails`() {
        val subject = responseCharacter.copy(created = "failed parsing")

        val result = subject.toCharacter()

        assertEquals(subject.created, result.created)
    }

    @Test
    fun `verify parsed string is returned when parsing succeeds`() {
        val subject = responseCharacter.copy(created = "2017-11-04T18:48:46.250Z")

        val result = subject.toCharacter()

        assertEquals("Sat, 4 Nov 2017", result.created)
    }
}