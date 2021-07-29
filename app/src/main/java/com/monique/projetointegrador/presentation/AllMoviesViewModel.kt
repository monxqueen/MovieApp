package com.monique.projetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.data.repository.FavoriteMoviesRepositoryImpl
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
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

class AllMoviesViewModel: ViewModel() {

    private val getAllMoviesUseCase = GetAllMoviesUseCase()
    private val getGenresUseCase = GetGenresUseCase()
    private val getMoviesByGenreUseCase = GetMoviesByGenreUseCase()
    private val favoriteMoviesUseCase = FavoriteMoviesUseCase()

    private val _moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val movieListLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

    private val disposable = CompositeDisposable()

    fun getMovies(){
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

    fun favoriteMovie(movie: Movie){
        favoriteMoviesUseCase.addFavoriteMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    print("successfully added to favorites")
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
                    print("successfully removed from favorites")
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    //gerenciamento de memória
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }
}