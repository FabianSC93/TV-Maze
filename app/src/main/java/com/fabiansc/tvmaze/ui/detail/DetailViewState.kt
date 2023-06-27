package com.fabiansc.tvmaze.ui.detail

import com.fabiansc.tvmaze.data.DataResult
import com.fabiansc.tvmaze.data.DataResultError
import com.fabiansc.tvmaze.data.network.response.Show
import com.fabiansc.tvmaze.domain.model.TvShowDetailModel
import com.fabiansc.tvmaze.domain.model.TvShowModel

sealed class DetailViewState {
    object Start : DetailViewState()
    object Loading : DetailViewState()
    class Error (val type: DataResultError) : DetailViewState()
    class Success (val data: TvShowDetailModel) : DetailViewState()
}