package com.monique.projetointegrador.data.localsource

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.monique.projetointegrador.data.model.movies.MovieResponse
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.presentation.FavoriteMoviesFragment
import io.reactivex.Single
import java.lang.IllegalStateException

object MovieLocalDataSourceImpl: MovieLocalDataSource{

    private val myPreferences: SharedPreferences? = FavoriteMoviesFragment().activity?.getPreferences(MODE_PRIVATE)
    private val gson = Gson()
    private val favoriteMoviesList = mutableListOf<MovieResponse>()

    override fun favoriteMovie(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            val result = favoriteMoviesList.add(movie) //result is either true or false
            if (result) {
                /*val prefsEditor = myPreferences?.edit()
                val json: String = gson.toJson(favoriteMoviesList)
                prefsEditor?.putString("favoriteMoviesList", json)
                prefsEditor?.apply()*/
                emitter.onSuccess(favoriteMoviesList)
            } else {
                emitter.onError(IllegalStateException())
            }
        }
    }

    override fun unfavoriteMovie(movie: MovieResponse): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            val movieToRemove = favoriteMoviesList.find {
                it.id == movie.id
            }
            val result = favoriteMoviesList.remove(movieToRemove)
            if (result) {
                emitter.onSuccess(favoriteMoviesList)
            } else {
                emitter.onError(IllegalStateException())
            }
        }
    }

    override fun getFavoriteMovies(): Single<List<MovieResponse>> {
        return Single.create { emitter ->
            /*val json = myPreferences?.getString("favoriteMoviesList", "")
            val listFromPrefs = gson.fromJson(json, favoriteMoviesList.javaClass)
            emitter.onSuccess(listFromPrefs)*/

            emitter.onSuccess(favoriteMoviesList)
        }
    }

    override fun checkIfFavorite(movie: MovieResponse): Single<Boolean> {
        return Single.create { emitter ->
            val result = favoriteMoviesList.contains(movie) //result is either true or false
            if (result) {
                emitter.onSuccess(true)
            } else {
                emitter.onSuccess(false)
            }
        }
    }

}