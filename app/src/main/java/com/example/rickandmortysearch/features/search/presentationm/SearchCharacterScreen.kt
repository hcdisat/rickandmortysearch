package com.example.rickandmortysearch.features.search.presentationm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortysearch.R
import com.example.rickandmortysearch.ui.theme.RickAndMortySearchTheme
import com.example.rickandmortysearch.utils.getPreviewData

@Composable
fun SearchCharacterScreen(
    modifier: Modifier = Modifier,
    state: SearchCharacterState = SearchCharacterState(),
    onEvent: (SearchCharacterEvent) -> Unit = {}
) {
    val columns = getLayoutColumnsNumber()
    val gridState = rememberLazyGridState()
    if (state.selectedCharacter == null) {
        SearchScreen(
            modifier = modifier,
            state = state,
            columns = columns,
            gridState = gridState,
            onSearchValueChange = { onEvent(UpdateSearchQuery(it)) },
            onCharacterSelected = { onEvent(CharacterSelected(it)) }
        )
    } else {
        CharacterDetailsScreen(
            modifier = modifier,
            character = state.selectedCharacter,
            columns = columns
        ) {
            onEvent(CharacterSelected(null))
        }
    }
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchCharacterState = SearchCharacterState(),
    gridState: LazyGridState = rememberLazyGridState(),
    columns: Int = 1,
    onSearchValueChange: (String) -> Unit = {},
    onCharacterSelected: (UICharacter) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var previousQuery by remember { mutableStateOf(state.query) }

    // Scroll to top when query changes and results are successful
    LaunchedEffect(state.query) {
        if (state.query != previousQuery) {
            previousQuery = state.query
            if (state.query.isNotEmpty() && state.searchResult is PresentationSearchResult.Success) {
                gridState.scrollToItem(0)
            }
        }
    }

    // Hide keyboard when loading completes
    LaunchedEffect(state.isLoading) {
        if (!state.isLoading && state.query.isNotEmpty()) {
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.query,
            onValueChange = onSearchValueChange,
            placeholder = {
                Text(stringResource(R.string.search_characters_search_bar_place_holder))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_character_search_icon_content_description)
                )
            },
            trailingIcon = {
                if (state.query.isNotEmpty()) {
                    IconButton(onClick = { onSearchValueChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.clear_search_clear_button_text)
                        )
                    }
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        if (state.isLoading) {
            RenderLoadingState()
        }

        if (state.hasError) {
            RenderErrorState()
        }

        when (state.searchResult) {
            PresentationSearchResult.Empty -> RenderEmptyState()

            PresentationSearchResult.NoResult -> RenderNoCharacterFoundState()

            is PresentationSearchResult.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    state = gridState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(
                        items = state.searchResult.characters,
                        key = { it.id }
                    ) { character ->
                        RenderCharacter(
                            character = character,
                            onCharacterClick = onCharacterSelected
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    character: UICharacter,
    columns: Int = 1,
    onBackClick: () -> Unit,
) {
    val useTwoColumns by remember {
        derivedStateOf { columns >= 2 }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_nav_text)
            )
        }

        if (useTwoColumns) {
            // Landscape/Tablet layout: Image and name on left, details on right
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Left column: Name and Image
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Right column: Details
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = character.details.toList(),
                        key = { it.label }
                    ) { (label, value) ->
                        DetailItem(
                            label = label,
                            value = value
                        )
                    }
                }
            }
        } else {
            // Portrait/Phone layout: Vertical
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                item {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                items(
                    items = character.details.toList(),
                    key = { it.label }
                ) { (label, value) ->
                    DetailItem(
                        label = label,
                        value = value
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
private fun RenderCharacter(
    modifier: Modifier = Modifier,
    character: UICharacter,
    onCharacterClick: (UICharacter) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCharacterClick(character) }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = character.name,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun RenderLoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun RenderErrorState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.an_error_occurred_try_again_later),
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun RenderNoCharacterFoundState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(R.string.search_character_not_found),
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )

        Text(
            text = stringResource(R.string.search_character_empty_search_text)
        )
    }
}

@Composable
private fun RenderEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = stringResource(R.string.search_character_search_icon_content_description),
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )

        Text(
            text = stringResource(R.string.search_character_screen_empty_state_text)
        )
    }
}

/**
 * Use 2 columns for screens >= 600dp (phone landscape or tablets)
 */
@Composable
private fun getLayoutColumnsNumber(): Int {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    val containerWidthDp = with(density) { windowInfo.containerSize.width.toDp() }
    return when {
        containerWidthDp >= 600.dp -> 2
        else -> 1
    }
}

@Preview(showBackground = true)
@Composable
fun SearchCharacterScreenPreview(@PreviewParameter(SearchCharacterScreenPreviewArgs::class) state: SearchCharacterState) {
    RickAndMortySearchTheme {
        SearchCharacterScreen(state = state)
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterDetailsScreenPreview() {
    RickAndMortySearchTheme {
        CharacterDetailsScreen(
            character = getPreviewData().first(),
            onBackClick = {}
        )
    }
}

class SearchCharacterScreenPreviewArgs : PreviewParameterProvider<SearchCharacterState> {
    override val values: Sequence<SearchCharacterState>
        get() = sequenceOf(
            SearchCharacterState(),
            SearchCharacterState(isLoading = true),
            SearchCharacterState(hasError = true),
            SearchCharacterState(searchResult = PresentationSearchResult.NoResult),
            SearchCharacterState(
                searchResult = PresentationSearchResult.Success(getPreviewData())
            ),
        )
}