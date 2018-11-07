package com.example.mhmacedo.popularmovies.model

data class Film(
    val path: String,
    val id: Float,
    val title: String,
    val year: Int,
    val overview: String,
    val release_date: String,
    val vote_average: Float
)