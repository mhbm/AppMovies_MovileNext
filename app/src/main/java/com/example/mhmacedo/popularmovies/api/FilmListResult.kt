package com.example.mhmacedo.popularmovies.api

import com.example.mhmacedo.popularmovies.model.Film
import com.google.gson.annotations.SerializedName

data class FilmListResult(
    @SerializedName(value = "items")
    val films: List<Film>
)
