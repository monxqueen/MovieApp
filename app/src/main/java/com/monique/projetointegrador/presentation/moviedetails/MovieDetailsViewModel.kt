package com.monique.projetointegrador.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.domain.model.Cast
import com.monique.projetointegrador.domain.model.Certification
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.domain.model.MovieDetail
import com.monique.projetointegrador.domain.usecase.FavoriteMoviesUseCase
import com.monique.projetointegrador.domain.usecase.GetMovieDetailsUseCase
import com.monique.projetointegrador.presentation.model.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel: ViewModel() {

    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()

    private val _movieLiveData = MutableLiveData<MovieDetail>()
    val movieLiveData: LiveData<MovieDetail> = _movieLiveData

    private val _castLiveData = MutableLiveData<List<Cast>>()
    val castLiveData: LiveData<List<Cast>> = _castLiveData

    private val _certificationLiveData = MutableLiveData<List<Certification>>()
    val certificationLiveData: LiveData<List<Certification>> = _certificationLiveData

    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val favoriteMoviesLiveData : LiveData<List<Movie>> = _favoriteMoviesLiveData

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData : LiveData<ViewState> = _viewStateLiveData

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
                    _viewStateLiveData.value = ViewState.GeneralError
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
                    _viewStateLiveData.value = ViewState.GeneralError
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
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }
}