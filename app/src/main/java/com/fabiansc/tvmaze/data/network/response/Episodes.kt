package com.fabiansc.tvmaze.data.network.response

data class Episodes(
    val id: Int,
    val name: String,
    val url: String,
    val season: Int,
    val number: Int,
    val type: String,
    val airdate: String,
    val airtime: String,
    val airstamp: String,
    val runtime: Int,
    val rating: Rating,
    val image: Image? = null,
    val summary: String,
    val links: ShowLinks,
)