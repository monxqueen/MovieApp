//package com.monique.projetointegrador.data.localsource
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.monique.projetointegrador.domain.Movie
//
//@Dao
//interface FavoriteMovieDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(vararg movie: Movie)
//
//    @Query("SELECT * FROM movie WHERE isfavorite = :trueOrFalse") //1 == true in SQLite
//    fun loadFavoriteMovies(trueOrFalse: Boolean = true): MutableList<Movie>
//
//}