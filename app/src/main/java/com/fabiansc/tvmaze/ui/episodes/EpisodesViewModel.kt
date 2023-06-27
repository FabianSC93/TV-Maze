package com.fabiansc.tvmaze.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiansc.tvmaze.data.DataResult
import com.fabiansc.tvmaze.data.repository.TvShowRepository
import com.fabiansc.tvmaze.ui.detail.DetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
): ViewModel() {

    private val _uiEpisodesState = MutableStateFlow<EpisodesViewState> (EpisodesViewState.Start)
    val uiEpisodesState = _uiEpisodesState.asStateFlow()

    fun getEpisodes(id: String) {
        viewModelScope.launch {
            tvShowRepository.getEpisodes(id).collectLatest { result ->
                when(result) {
                    DataResult.Loading -> _uiEpisodesState.value = EpisodesViewState.Loading
                    is DataResult.Success -> _uiEpisodesState.value = EpisodesViewState.Success(result.data)
                    is DataResult.Error -> _uiEpisodesState.value = EpisodesViewState.Error(result.errorType)
                }
            }
        }
    }
}