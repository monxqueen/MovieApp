//package com.monique.projetointegrador.data.localsource
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.monique.projetointegrador.domain.model.Movie
//
//@Database(entities = arrayOf(Movie::class), version = 1)
//abstract class AppDatabase: RoomDatabase(){
//    abstract fun favoriteMoviesDao(): FavoriteMovieDao
//    abstract fun genreMovieBindingDao(): GenreMovieBindingDao
//
//    companion object {
//        @Volatile
//        var instance: AppDatabase? = null
//
//        @Synchronized
//        fun getInstance(context: Context): AppDatabase {
//            if(instance == null){
//                instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java, "favorite_movies_db"
//                ).build()
//            }
//            return instance as AppDatabase
//        }
//    }
//}