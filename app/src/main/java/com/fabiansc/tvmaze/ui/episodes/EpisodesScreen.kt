package com.fabiansc.tvmaze.ui.episodes

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fabiansc.tvmaze.data.DataResultError
import com.fabiansc.tvmaze.domain.model.EpisodeModel
import com.fabiansc.tvmaze.ui.compose.composables.SecondaryTopBar
import com.fabiansc.tvmaze.R
import com.fabiansc.tvmaze.ui.compose.composables.EpisodeItem
import com.fabiansc.tvmaze.ui.compose.composables.ErrorMessage

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = viewModel(),
    id: String,
    backPressed: () -> Unit
) {
    val episodesState = viewModel.uiEpisodesState.collectAsState().value

    var episodes: List<EpisodeModel> = emptyList()
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var error: DataResultError = DataResultError.NoError


    LaunchedEffect(key1 = Unit, block = {
        viewModel.getEpisodes(id)
    })

    when (episodesState) {
        EpisodesViewState.Loading -> isLoading = true
        is EpisodesViewState.Success -> {
            episodes = episodesState.episodes
            isLoading = false
            isError = false
        }
        is EpisodesViewState.Error -> {
            isError = true
            isLoading = false
            error = episodesState.error
        }
        else -> {}

    }

    Scaffold(topBar = {
        SecondaryTopBar() {
            backPressed()
        }
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
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val group = episodes.groupBy { episode ->
                        episode.season
                    }

                    group.forEach { (header, items) ->
                        stickyHeader {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .background(Color.DarkGray)
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    text = stringResource(id = R.string.txt_label_temporada, header),
                                    color = Color.White
                                )
                            }
                        }
                        items(items) { episode ->
                            EpisodeItem(episode)
                        }
                    }
                }
            }
        }
    }
}