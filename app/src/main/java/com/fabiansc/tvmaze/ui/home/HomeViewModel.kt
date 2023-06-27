package com.fabiansc.tvmaze.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiansc.tvmaze.core.getShortDate
import com.fabiansc.tvmaze.data.DataResult
import com.fabiansc.tvmaze.data.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    private val _uiTvShowsState = MutableStateFlow<HomeViewState>(HomeViewState.Start)
    val uiTvShowsState = _uiTvShowsState.asStateFlow()

    private val _uiSearchedShowsState = MutableStateFlow<HomeViewState>(HomeViewState.Start)
    val uiSearchedShowsState = _uiSearchedShowsState.asStateFlow()

    init{
        getTodayTvShows()
    }

    fun getTodayTvShows() {
        viewModelScope.launch {
            tvShowRepository.getTodayTvShows(Date().getShortDate()).collectLatest { result ->
                when(result){
                    DataResult.Loading -> _uiTvShowsState.value = HomeViewState.Loading
                    is DataResult.Success -> _uiTvShowsState.value = HomeViewState.Success(result.data)
                    is DataResult.Error -> _uiTvShowsState.value = HomeViewState.Error(result.errorType)
                }
            }
        }
    }

    fun searchShow(query: String) {
        viewModelScope.launch {
            tvShowRepository.searchShow(query).collectLatest { result ->
                when(result){
                    DataResult.Loading -> _uiSearchedShowsState.value = HomeViewState.Loading
                    is DataResult.Success -> _uiSearchedShowsState.value = HomeViewState.Success(result.data)
                    is DataResult.Error -> _uiSearchedShowsState.value = HomeViewState.Error(result.errorType)
                }
                _uiTvShowsState.value = HomeViewState.Start
            }
        }
    }

    fun resetSearchedShowState(){
        _uiSearchedShowsState.value = HomeViewState.Start
    }
}