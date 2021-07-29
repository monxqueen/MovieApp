package com.monique.projetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.domain.usecase.FavoriteMoviesUseCase
import com.monique.projetointegrador.domain.usecase.GetAllMoviesUseCase
import com.monique.projetointegrador.domain.usecase.GetGenresUseCase
import com.monique.projetointegrador.domain.usecase.GetMoviesByGenreUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel: ViewModel() {

    private val getAllMoviesUseCase = GetAllMoviesUseCase()
    private val getGenresUseCase = GetGenresUseCase()
    private val getMoviesByGenreUseCase = GetMoviesByGenreUseCase()
    private val favoriteMoviesUseCase = FavoriteMoviesUseCase()

    private val _moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val movieListLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val favoriteMoviesLiveData : LiveData<List<Movie>> = _favoriteMoviesLiveData

    private val disposable = CompositeDisposable()

    fun getPopularMovies(){
        getAllMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->
                    _moviesLiveData.value = result
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    fun getMoviesByGenre(genresId: List<Int>){
        getMoviesByGenreUseCase.executeMoviesByGenre(genresId.joinToString(","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {result ->
                    _moviesLiveData.value = result
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

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
                    _favoriteMoviesLiveData.value = it
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    fun favoriteMovie(movie: Movie){
        favoriteMoviesUseCase.addFavoriteMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _favoriteMoviesLiveData.value = it
                    checkFavorites()
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
                    _favoriteMoviesLiveData.value = it
                    checkFavorites()
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    fun checkFavorites(){
        favoriteMoviesLiveData.value?.forEach{ movie ->
            favoriteMoviesUseCase.isFavorite(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ result ->
                    movie.isFavorite = result
                }
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }

}