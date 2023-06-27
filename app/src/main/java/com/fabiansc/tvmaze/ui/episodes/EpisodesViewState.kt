package com.fabiansc.tvmaze.ui.episodes

import com.fabiansc.tvmaze.data.DataResultError
import com.fabiansc.tvmaze.domain.model.EpisodeModel
import com.fabiansc.tvmaze.domain.model.TvShowModel

sealed class EpisodesViewState {
    object Start : EpisodesViewState()
    object Loading : EpisodesViewState()
    class Success(val episodes: List<EpisodeModel>) : EpisodesViewState()
    class Error(val error: DataResultError) : EpisodesViewState()
}