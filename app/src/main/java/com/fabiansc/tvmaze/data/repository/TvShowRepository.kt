package com.fabiansc.tvmaze.data.repository

import android.content.Context
import com.fabiansc.tvmaze.core.InternetUtils
import com.fabiansc.tvmaze.data.DataResult
import com.fabiansc.tvmaze.data.DataResultError
import com.fabiansc.tvmaze.data.mapper.toEpisodeModel
import com.fabiansc.tvmaze.data.mapper.toPersonModel
import com.fabiansc.tvmaze.data.mapper.toTvShowModel
import com.fabiansc.tvmaze.data.network.ApiService
import com.fabiansc.tvmaze.data.network.response.SearchedShow
import com.fabiansc.tvmaze.data.network.response.TvShow
import com.fabiansc.tvmaze.domain.model.EpisodeModel
import com.fabiansc.tvmaze.domain.model.TvShowDetailModel
import com.fabiansc.tvmaze.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val apiService: ApiService,
    private val context: Context
) {
    suspend fun getTodayTvShows(todayDate: String): Flow<DataResult<List<TvShowModel>, DataResultError>> =
        flow {
            if (InternetUtils.isConnectedToInternet(context)) {
                emit(DataResult.Loading)
                apiService.getTodayTvShows(todayDate).let {
                    if (it.isNotEmpty()) {
                        it.map { tvShow ->
                            tvShow.toTvShowModel()
                        }.let { list ->
                            emit(DataResult.Success(list))
                        }
                    } else emit(DataResult.Error(DataResultError.EmptyResult))
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternet))
            }
        }

    suspend fun searchShow(query: String): Flow<DataResult<List<TvShowModel>, DataResultError>> =
        flow {
            if (InternetUtils.isConnectedToInternet(context)) {
                emit(DataResult.Loading)
                apiService.searchShow(query).let {
                    if (it.isNotEmpty()) {
                        it.map { searchShow ->
                            searchShow.toTvShowModel()
                        }.let { list ->
                            emit(DataResult.Success(list))
                        }
                    } else emit(DataResult.Error(DataResultError.EmptyResult))
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternet))
            }
        }

    suspend fun getDetailTvShow(id: String): Flow<DataResult<TvShowDetailModel, DataResultError>> =
        flow {
            if (InternetUtils.isConnectedToInternet(context)) {
                emit(DataResult.Loading)
                val persons = apiService.getPersonsTvShow(id)
                apiService.getDetailTvShow(id).let { show ->
                    if (show != null) {
                        emit(DataResult.Success(show.toTvShowModel(persons.map { cast ->
                            cast.toPersonModel()
                        })))
                    } else emit(DataResult.Error(DataResultError.EmptyResult))
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternet))
            }
        }

    suspend fun getEpisodes(id: String): Flow<DataResult<List<EpisodeModel>, DataResultError>> =
        flow {
            if (InternetUtils.isConnectedToInternet(context)) {
                emit(DataResult.Loading)
                apiService.getEpisodes(id).let {
                    if (it.isNotEmpty()) {
                        it.map { episode ->
                            episode.toEpisodeModel()
                        }.let { list ->
                            emit(DataResult.Success(list))
                        }
                    } else emit(DataResult.Error(DataResultError.EmptyResult))
                }
            } else {
                emit(DataResult.Error(DataResultError.NoInternet))
            }
        }
}