package com.example.mhmacedo.popularmovies.dao

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    val id: Float,

    @ColumnInfo(name = "title")
    @SerializedName(value = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    @SerializedName(value = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    @SerializedName(value = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    @SerializedName(value = "vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "poster_path")
    @SerializedName(value = "poster_path")
    val poster_path: String

) : Serializable