package com.fabiansc.tvmaze.data.network.response

data class TvShow(
    val id: Int,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    val airdate: String,
    val airtime: String,
    val airstamp: String,
    val runtime: Int,
    val rating: Rating,
    val image: Image? = null,
    val summary: String? = null,
    val show: Show
)

data class Rating(
    val average: Double? = null
)

data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language:String,
    val genres: List<String>,
    val status:String,
    val runtime: Int,
    val averageRuntime: Int,
    val premiered: String,
    val ended: String,
    val officialSite: String? = null,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Int,
    val network:Network? = null,
    val image:Image? = null,
    val summary: String? = null,
    val updated: Long,
    val links: ShowLinks? = null
)

data class Schedule(
    val time: String,
    val days: List<String>
)

data class Network(
    val id: Int,
    val name: String,
    val country: Country,
    val officialSite: String
)

data class Image (
    val medium: String? = null,
    val original: String
)

data class Country(
    val name: String,
    val code: String,
    val timezone: String
)

data class ShowLinks (
    val self: Self? = null,
)

data class Self (
    val href: String
)