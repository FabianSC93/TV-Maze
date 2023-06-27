package com.fabiansc.tvmaze.ui.home

import com.fabiansc.tvmaze.data.DataResultError
import com.fabiansc.tvmaze.domain.model.TvShowModel

sealed class HomeViewState {
    object Start : HomeViewState()
    object Loading : HomeViewState()
    class Success(val shows: List<TvShowModel>) : HomeViewState()
    class Error(val error: DataResultError) : HomeViewState()
}