package com.fabiansc.tvmaze.data.network

import com.fabiansc.tvmaze.data.network.response.*
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getTodayTvShows(todayDate: String): List<TvShow> {
        val response = apiClient.getTodayTvShows(date = todayDate)
        return response.body() ?: emptyList()
    }

    suspend fun searchShow(query: String): List<SearchedShow> {
        val response = apiClient.searchShow(query)
        return response.body() ?: emptyList()
    }

    suspend fun getDetailTvShow(id: String): Show? {
        val response = apiClient.getDetailTvShow(id)
        return response.body()
    }

    suspend fun getPersonsTvShow(id: String): List<Cast> {
        val response = apiClient.getPersonsTvShow(id)
        return response.body() ?: emptyList()
    }

    suspend fun getEpisodes(id: String): List<Episodes> {
        val response = apiClient.getEpisodes(id)
        return response.body() ?: emptyList()
    }
}