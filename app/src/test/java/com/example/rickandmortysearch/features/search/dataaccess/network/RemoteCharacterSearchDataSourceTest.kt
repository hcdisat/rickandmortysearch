package com.example.rickandmortysearch.features.search.dataaccess.network

import com.example.rickandmortysearch.features.search.dataaccess.EmptySearchResult
import com.example.rickandmortysearch.features.search.dataaccess.SuccessSearchResult
import com.example.rickandmortysearch.features.search.dataaccess.model.CharacterResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteCharacterSearchDataSourceTest {
    private val service: CharacterSearchService = mockk()
    private lateinit var dataSource: RemoteCharacterSearchDataSource

    @Before
    fun setUp() {
        dataSource = RemoteCharacterSearchDataSource(service)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `verify EmptySearchResult is returned when response is not successful`() = runTest {
        mockRequest {
            every { isSuccessful } returns false
        }

        val result = dataSource.getCharacters(QUERY)

        assertEquals(EmptySearchResult, result)
    }

    @Test
    fun `verify EmptySearchResult is returned when response body is null`() = runTest {
        mockRequest {
            every { isSuccessful } returns true
            every { body() } returns null
        }

        val result = dataSource.getCharacters(QUERY)

        assertEquals(EmptySearchResult, result)
    }

    @Test
    fun `verify SuccessSearchResult is returned when response is successful`() = runTest {
        mockRequest {
            every { isSuccessful } returns true
            every { body() } returns mockk(relaxed = true)
        }

        val result = dataSource.getCharacters(QUERY)

        assertTrue(result is SuccessSearchResult)
    }

    private inline fun mockRequest(transform: Response<CharacterResponse>.() -> Unit) {
        val query = QUERY
        val response = mockk<Response<CharacterResponse>>()
        coEvery { service.searchCharacters(query) } returns response
        response.transform()
    }

    companion object {
        private const val QUERY = "Rick"

    }
}