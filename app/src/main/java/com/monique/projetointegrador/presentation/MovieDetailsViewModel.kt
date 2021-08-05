package com.monique.projetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.domain.Cast
import com.monique.projetointegrador.domain.Certification
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.domain.MovieDetail
import com.monique.projetointegrador.domain.usecase.FavoriteMoviesUseCase
import com.monique.projetointegrador.domain.usecase.GetMovieDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel: ViewModel() {

    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()
    private val favoriteMoviesUseCase = FavoriteMoviesUseCase()

    private val _movieLiveData = MutableLiveData<MovieDetail>()
    val movieLiveData: LiveData<MovieDetail> = _movieLiveData
    private val _castLiveData = MutableLiveData<List<Cast>>()
    val castLiveData: LiveData<List<Cast>> = _castLiveData
    private val _certificationLiveData = MutableLiveData<List<Certification>>()
    val certificationLiveData: LiveData<List<Certification>> = _certificationLiveData
    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val favoriteMoviesLiveData : LiveData<List<Movie>> = _favoriteMoviesLiveData

    private val disposable = CompositeDisposable()

    fun getMovieDetails(movieId: Int){
        getMovieDetailsUseCase.executeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _movieLiveData.value = result
                },
                {

                }
            ).handleDisposable()
    }

    fun getCast(movieId: Int){
        getMovieDetailsUseCase.executeCast(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _castLiveData.value = result
                },
                {
                    TODO()
                }
            ).handleDisposable()
    }

    fun getCertification(movieId: Int){
        getMovieDetailsUseCase.executeCertification(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _certificationLiveData.value = result
                },
                {
                    TODO()
                }
            ).handleDisposable()
    }

    fun removeFromFavorites(movie: MovieDetail){
        val genreIdsList = mutableListOf<Int>()
        movie.genres.forEach { genre ->
            genreIdsList.add(genre.id)
        }
        val mappedMovie = Movie(
            id = movie.id,
            title = movie.title,
            genreIds = genreIdsList,
            isFavorite = false
        )
        favoriteMoviesUseCase.removeFavoriteMovie(mappedMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _favoriteMoviesLiveData.value = it
                },
                {
                    TODO()
                }
            ).handleDisposable()
    }

    //não funciona como deveria (classe moviedetail precisa de um poster_path)
    fun addToFavorites(movie: MovieDetail){
        val genreIdsList = mutableListOf<Int>()
        movie.genres.forEach { genre ->
            genreIdsList.add(genre.id)
        }
        val mappedMovie = Movie(
            id = movie.id,
            title = movie.title,
            genreIds = genreIdsList,
            isFavorite = false
        )
        favoriteMoviesUseCase.addFavoriteMovie(mappedMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _favoriteMoviesLiveData.value = it
                },
                {
                    TODO()
                }
            ).handleDisposable()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }
}