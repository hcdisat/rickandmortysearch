@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.rickandmortysearch.features.search.presentationm

import app.cash.turbine.test
import com.example.rickandmortysearch.features.search.dataaccess.model.transformer.toCharacter
import com.example.rickandmortysearch.features.search.domain.model.CharacterSearchResult
import com.example.rickandmortysearch.features.search.domain.usecase.SearchCharacterByNameUseCase
import com.example.rickandmortysearch.utils.getCharacterData
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchCharacterViewModelTest {
    private val scheduler = TestCoroutineScheduler()
    private val dispatcher = StandardTestDispatcher(scheduler)
    private val searchCharacterByName: SearchCharacterByNameUseCase = mockk()
    private lateinit var viewModel: SearchCharacterViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = SearchCharacterViewModel(dispatcher, searchCharacterByName)
        mockUseCaseResult(true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `verify initial state`() = runTest {
        viewModel.state.test {
            val state = awaitItem()
            assertTrue(state.query.isEmpty())
            assertFalse(state.isLoading)
            assertFalse(state.hasError)
            assertTrue(state.searchResult is PresentationSearchResult.Empty)
            assertNull(state.selectedCharacter)
        }
    }

    @Test
    fun `verify query is updated correctly`() = runTest {
        viewModel.state.test {
            var state = awaitItem()
            assertTrue(state.query.isEmpty())
            assertFalse(state.isLoading)
            assertFalse(state.hasError)
            assertTrue(state.searchResult is PresentationSearchResult.Empty)
            assertNull(state.selectedCharacter)

            viewModel.onEvent(UpdateSearchQuery("query"))
            state = awaitItem()
            assertEquals("query", state.query)

            viewModel.onEvent(UpdateSearchQuery(""))
            state = awaitItem()
            assertTrue(state.query.isBlank())
        }
    }

    @Test
    fun `verify query is executed when the query changes and there is results`() = runTest {
        advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)

        viewModel.onEvent(UpdateSearchQuery("query"))
        advanceUntilIdle()

        viewModel.state.test {
            val state = awaitItem()
            assertEquals("query", state.query)
            assertFalse(state.isLoading)
            assertFalse(state.hasError)
            assertTrue(state.searchResult is PresentationSearchResult.Success)
            assertNull(state.selectedCharacter)
        }
    }

    @Test
    fun `verify query is executed when the query changes and there is no results`() = runTest {
        mockUseCaseResult(false)
        advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)

        viewModel.onEvent(UpdateSearchQuery("query"))
        advanceUntilIdle()

        viewModel.state.test {
            val state = awaitItem()
            assertEquals("query", state.query)
            assertFalse(state.isLoading)
            assertFalse(state.hasError)
            assertTrue(state.searchResult is PresentationSearchResult.NoResult)
            assertNull(state.selectedCharacter)
        }
    }

    @Test
    fun `verify error state is set when the query fails`() = runTest {
        coEvery {
            searchCharacterByName.invoke(any())
        } returns Result.failure(RuntimeException())
        advanceUntilIdle()

        viewModel.onEvent(UpdateSearchQuery("query"))
        advanceUntilIdle()

        viewModel.state.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.hasError)
            assertNull(state.selectedCharacter)
        }
    }

    @Test
    fun `verify selected character is set correctly`() = runTest {
        advanceUntilIdle()
        val character = getCharacterData()
            .first()
            .toCharacter()
            .toCharacter()

        viewModel.onEvent(CharacterSelected(character))

        viewModel.state.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertFalse(state.hasError)
            assertEquals(character,  state.selectedCharacter)
        }
    }

    private fun mockUseCaseResult(isSuccess: Boolean) {
        coEvery {
            searchCharacterByName.invoke(any())
        } answers {
            val answerWith = if (isSuccess) {
                CharacterSearchResult
                    .Success(getCharacterData().map { it.toCharacter() })
            } else {
                CharacterSearchResult.Empty
            }

            Result.success(answerWith)
        }
    }
}