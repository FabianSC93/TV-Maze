package com.fabiansc.tvmaze.domain.model

import com.fabiansc.tvmaze.data.network.response.Person

data class TvShowDetailModel(
    val id: Int,
    val image: String,
    val name: String,
    val network: String,
    val dates: String,
    val rating: Double,
    val summary: String,
    val genders: String,
    val link: String,
    val persons: List<PersonModel>
)
