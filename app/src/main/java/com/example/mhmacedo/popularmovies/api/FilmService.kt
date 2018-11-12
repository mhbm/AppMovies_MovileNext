package com.example.mhmacedo.popularmovies.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmService {
    @GET("/3/movie/top_rated")
    fun listTopRated(
        @Query("api_key") api_key: String
    ): Call<FilmListResult>
}