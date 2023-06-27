package com.fabiansc.tvmaze.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiansc.tvmaze.data.DataResult
import com.fabiansc.tvmaze.data.repository.TvShowRepository
import com.fabiansc.tvmaze.ui.home.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
): ViewModel() {

    private val _uiDetailState = MutableStateFlow<DetailViewState>(DetailViewState.Start)
    val uiDetailState = _uiDetailState.asStateFlow()

    fun getDetailTvShow(id: String) {
        viewModelScope.launch {
            tvShowRepository.getDetailTvShow(id).collectLatest { result ->
                when(result) {
                    DataResult.Loading -> _uiDetailState.value = DetailViewState.Loading
                    is DataResult.Success -> _uiDetailState.value = DetailViewState.Success(result.data)
                    is DataResult.Error -> _uiDetailState.value = DetailViewState.Error(result.errorType)
                }
            }
        }
    }

    fun reset(){
        _uiDetailState.value = DetailViewState.Start
    }
}