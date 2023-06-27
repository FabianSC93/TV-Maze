package com.fabiansc.tvmaze.data.network

import com.fabiansc.tvmaze.data.network.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET ("schedule")
    suspend fun getTodayTvShows(
        @Query("country") country:String = "US",
        @Query("date") date:String
    ): Response<List<TvShow>>

    @GET ("search/shows")
    suspend fun searchShow(
        @Query("q") show:String,
    ): Response<List<SearchedShow>>

    @GET("/shows/{id}")
    suspend fun getDetailTvShow(
        @Path("id") id: String
    ): Response<Show>

    @GET("/shows/{id}/cast")
    suspend fun getPersonsTvShow(
        @Path("id") id: String
    ): Response<List<Cast>>

    @GET("/shows/{id}/episodes")
    suspend fun getEpisodes(
        @Path("id") id: String
    ): Response<List<Episodes>>
}