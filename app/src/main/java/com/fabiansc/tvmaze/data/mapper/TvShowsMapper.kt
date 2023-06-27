package com.fabiansc.tvmaze.data.mapper

import com.fabiansc.tvmaze.core.Constants.URL_NO_IMAGE
import com.fabiansc.tvmaze.core.convertToHtml
import com.fabiansc.tvmaze.data.network.response.*
import com.fabiansc.tvmaze.domain.model.EpisodeModel
import com.fabiansc.tvmaze.domain.model.PersonModel
import com.fabiansc.tvmaze.domain.model.TvShowDetailModel
import com.fabiansc.tvmaze.domain.model.TvShowModel

fun TvShow.toTvShowModel() = TvShowModel(
    show.id,
    show.image?.medium ?: URL_NO_IMAGE,
    show.name,
    show.network?.name ?: "",
    "$airdate | $airtime",
)

fun SearchedShow.toTvShowModel() = TvShowModel(
    show.id,
    show.image?.medium ?: URL_NO_IMAGE,
    show.name,
    show.network?.name ?: "",
    "${show.schedule.time} | ${show.schedule.days.joinToString(separator = ", ")}"
)

fun Show.toTvShowModel(persons: List<PersonModel>) = TvShowDetailModel(
    id,
    image?.medium ?: URL_NO_IMAGE,
    name,
    network?.name ?: "",
    "${schedule.time} | ${schedule.days.joinToString(separator = ", ")}",
    rating.average ?: 0.0,
    summary?.convertToHtml() ?: "",
    genres.joinToString(separator = ", "),
    officialSite ?: "",
    persons
)

fun Cast.toPersonModel() = PersonModel(
    person.id,
    person.name,
    person.image?.medium ?: URL_NO_IMAGE
)

fun Episodes.toEpisodeModel() = EpisodeModel(
    id,
    name,
    season,
    number,
    image?.medium ?: URL_NO_IMAGE
)