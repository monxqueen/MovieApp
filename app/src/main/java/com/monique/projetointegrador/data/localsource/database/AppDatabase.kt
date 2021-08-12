package com.monique.projetointegrador.data.localsource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieData::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun favoriteMoviesDao(): FavoriteMovieDao

}