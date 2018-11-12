package com.example.mhmacedo.popularmovies.model

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName(value = "poster_path")
    val poster_path: String,
    @SerializedName(value = "id")
    val id: Float,
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "overview")
    val overview: String,
    @SerializedName(value = "release_date")
    val release_date: String,
    @SerializedName(value = "vote_average")
    val vote_average: Double
)