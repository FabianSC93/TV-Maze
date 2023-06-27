package com.fabiansc.tvmaze.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fabiansc.tvmaze.data.DataResultError
import com.fabiansc.tvmaze.domain.model.TvShowModel
import com.fabiansc.tvmaze.ui.compose.composables.ErrorMessage
import com.fabiansc.tvmaze.ui.compose.composables.MainTopBar
import com.fabiansc.tvmaze.ui.compose.composables.OnLifecycleEvent
import com.fabiansc.tvmaze.ui.compose.composables.TvShowItem
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    clickItem: (String) -> Unit
) {

    val tvShowsState = viewModel.uiTvShowsState.collectAsState().value
    val searchedShows = viewModel.uiSearchedShowsState.collectAsState().value

    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var tvShows = emptyList<TvShowModel>()
    var error: DataResultError = DataResultError.NoError
    var isShowSearch by remember { mutableStateOf(false) }

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.getTodayTvShows()
            }
            else -> {}
        }
    }

    when (tvShowsState) {
        HomeViewState.Loading -> isLoading = true
        is HomeViewState.Success -> {
            tvShows = tvShowsState.shows
            isLoading = false
            isError = false
        }
        is HomeViewState.Error -> {
            isError = true
            isLoading = false
            error = tvShowsState.error
        }
        else -> {}

    }

    when (searchedShows) {
        HomeViewState.Loading -> isLoading = true
        is HomeViewState.Success -> {
            tvShows = searchedShows.shows
            isLoading = false
            isError = false
        }
        is HomeViewState.Error -> {
            error = searchedShows.error
            isLoading = false
            isError = true
        }
        else -> {}
    }

    Scaffold(topBar = {
        MainTopBar(
            onSearch = { query ->
                viewModel.searchShow(query)
                isShowSearch = true
            },
            backPressed = {
                if (isShowSearch) {
                    viewModel.getTodayTvShows()
                    viewModel.resetSearchedShowState()
                    isShowSearch = false
                }
            }
        )
    }) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 6.dp,
                        color = Color.Green
                    )
                }
            }

            isError -> {
                ErrorMessage(error)
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tvShows) {
                        TvShowItem(it) { id ->
                            clickItem(id)
                            viewModel.resetSearchedShowState()
                        }
                    }
                }
            }
        }
    }
}