package com.monique.projetointegrador.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.data.model.Genres
import com.monique.projetointegrador.data.model.Movies
import com.monique.projetointegrador.data.repository.Network
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllMoviesFragmentViewModel(): ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movies>>(mutableListOf())
    val moviesLiveData : LiveData<List<Movies>> = _moviesLiveData
    private val _genresLiveData = MutableLiveData<List<Genres>>()
    val genresLiveData : LiveData<List<Genres>> = _genresLiveData

    @SuppressLint("CheckResult")
    fun getMovies(){
        Network.getService().getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ response ->
                _moviesLiveData.value = response.movieResults
            }
    }

    @SuppressLint("CheckResult")
    fun getGenres(){
        Network.getService().getAllGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ response ->
                _genresLiveData.value = response.genres
            }
    }

    @SuppressLint("CheckResult")
    fun getMoviesByGenre(genresId: List<Int>){
        Network.getService().getMoviesByGenre(genresId.joinToString(","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                response ->
                _moviesLiveData.value = response.movieResults
            }
    }
}