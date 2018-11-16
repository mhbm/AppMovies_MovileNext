package com.example.mhmacedo.popularmovies.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase? {
            if (instance == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomDatabase::class.java,
                        "movie_database"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

    }

}
