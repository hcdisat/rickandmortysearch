package com.example.rickandmortysearch.features.search.presentationm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortysearch.features.search.domain.model.CharacterSearchResult
import com.example.rickandmortysearch.features.search.domain.usecase.SearchCharacterByNameUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

class SearchCharacterViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val searchCharacterByName: SearchCharacterByNameUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SearchCharacterState())
    val state = _state.asStateFlow()

    init {
        @OptIn(FlowPreview::class)
        state.map { it.query }
            .distinctUntilChanged()
            .debounce(1000L)
            .onEach { query -> executeSearch(query) }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }

    fun onEvent(event: SearchCharacterEvent) {
        when (event) {
            is UpdateSearchQuery -> updateSearchQuery(event.query)
            is CharacterSelected -> selectCharacter(event.character)
        }
    }

    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(query = query) }
    }

    private fun selectCharacter(selectedCharacter: UICharacter?) {
        _state.update { it.copy(selectedCharacter = selectedCharacter) }
    }

    private fun setLoadingState(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }

    private fun executeSearch(query: String) {
        query.ifBlank { return }
        viewModelScope.launch {
            setLoadingState(true)
            withContext(dispatcher) {
                delay(1.seconds)
                searchCharacterByName(query)
            }.mapCatching { result ->
                when (result) {
                    is CharacterSearchResult.Empty -> PresentationSearchResult.NoResult
                    is CharacterSearchResult.Success -> PresentationSearchResult.Success(
                        characters = result.characters.map { it.toCharacter() }.toPersistentList()
                    )
                }
            }.fold(
                onFailure = { _ ->
                    ensureActive()
                    _state.update { it.copy(hasError = true) }
                },
                onSuccess = { result ->
                    _state.update { it.copy(hasError = false, searchResult = result) }
                }
            ).also { setLoadingState(false) }
        }
    }
}

data class SearchCharacterState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val query: String = "",
    val searchResult: PresentationSearchResult = PresentationSearchResult.Empty,
    val selectedCharacter: UICharacter? = null
)

sealed interface PresentationSearchResult {
    data class Success(val characters: ImmutableList<UICharacter>) : PresentationSearchResult
    object Empty : PresentationSearchResult
    object NoResult : PresentationSearchResult
}

data class CharacterDetail(
    val label: String,
    val value: String
)

data class UICharacter(
    val id: Int,
    val image: String,
    val name: String,
    val details: ImmutableList<CharacterDetail>
)

sealed interface SearchCharacterEvent
data class UpdateSearchQuery(val query: String) : SearchCharacterEvent
data class CharacterSelected(val character: UICharacter?) : SearchCharacterEvent
