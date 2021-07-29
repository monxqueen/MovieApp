package com.monique.projetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.data.repository.FavoriteMoviesRepositoryImpl
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.domain.usecase.FavoriteMoviesUseCase
import com.monique.projetointegrador.domain.usecase.GetGenresUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteMoviesViewModel(): ViewModel() {

    private val favoriteMoviesUseCase = FavoriteMoviesUseCase()
    private val getGenresUseCase = GetGenresUseCase()

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

    private val _moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val movieListLiveData : LiveData<List<Movie>> = _moviesLiveData

    val disposable = CompositeDisposable()

    fun getGenres(){
        getGenresUseCase.executeGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->
                    _genresLiveData.value = result
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    fun getFavoriteMovies(){
        favoriteMoviesUseCase.getFavoriteMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _moviesLiveData.value = it
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    fun unfavoriteMovie(movie: Movie){
        favoriteMoviesUseCase.removeFavoriteMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _moviesLiveData.value = it
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }
}