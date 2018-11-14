package com.example.mhmacedo.popularmovies.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase(val movieInsert: Movie) : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: MovieRoomDatabase? = null

        fun getInstance(context: Context): MovieRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomDatabase::class.java,
                        "movie_database.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}
