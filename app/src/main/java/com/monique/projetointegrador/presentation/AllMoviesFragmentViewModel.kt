package com.monique.projetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AllMoviesFragmentViewModel: ViewModel() {

    private val moviesRepositoryImpl = MoviesRepositoryImpl()

    private val _moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val movieListLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

    private val disposable = CompositeDisposable()

    fun getMovies(){
        moviesRepositoryImpl.getPopularMovies()
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
        moviesRepositoryImpl.getAllGenres()
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
        moviesRepositoryImpl.getMoviesByGenre(genresId.joinToString(","))
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

    //gerenciamento de memória
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }
}