package com.fabiansc.tvmaze.data.network.response

data class Cast(
    val person: Person
)

data class Person(
    val id: Int,
    val name: String,
    val image: Image? = null,
)
