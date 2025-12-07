package com.example.rickandmortysearch.ui.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortysearch.features.search.presentationm.SearchCharacterScreen
import com.example.rickandmortysearch.features.search.presentationm.SearchCharacterViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object SearchRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SearchRoute,
        modifier = modifier
    ) {
        composable<SearchRoute> {
            val viewModel = koinViewModel<SearchCharacterViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            SearchCharacterScreen(state = state, onEvent = viewModel::onEvent)
        }
    }
}
