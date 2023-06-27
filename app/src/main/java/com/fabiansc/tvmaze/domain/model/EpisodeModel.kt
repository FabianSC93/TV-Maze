package com.fabiansc.tvmaze.domain.model

data class EpisodeModel(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val image: String
)