package com.example.mhmacedo.popularmovies.api

import com.example.mhmacedo.popularmovies.model.Film
import com.google.gson.annotations.SerializedName


data class FilmListResult(
    @SerializedName("page")
    var page: Int,
    @SerializedName("total_results")
    var totalResults: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("results")
    var results: List<Film>

)