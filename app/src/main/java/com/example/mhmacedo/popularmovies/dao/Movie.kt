package com.example.mhmacedo.popularmovies.dao

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Float,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "poster_path")
    val poster_path: String

)