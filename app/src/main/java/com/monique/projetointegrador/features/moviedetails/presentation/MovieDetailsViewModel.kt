package com.monique.projetointegrador.features.moviedetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monique.projetointegrador.features.moviedetails.domain.model.Cast
import com.monique.projetointegrador.features.moviedetails.domain.model.Certification
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import com.monique.projetointegrador.features.moviedetails.domain.GetMovieDetailsUseCase
import com.monique.projetointegrador.presentation.model.ViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

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

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase.executeMovie(movieId)
                .flowOn(dispatcher)
                .catch {
                    _viewStateLiveData.value = ViewState.GeneralError
                }
                .collect { result ->
                    _movieLiveData.value = result
                }
        }
    }

    fun getCast(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase.executeCast(movieId)
                .flowOn(dispatcher)
                .catch {
                    _viewStateLiveData.value = ViewState.GeneralError
                }
                .collect { result ->
                    _castLiveData.value = result
                }
        }
    }

    fun getCertification(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase.executeCertification(movieId)
                .flowOn(dispatcher)
                .catch {
                    _viewStateLiveData.value = ViewState.GeneralError
                }
                .collect {result ->
                    _certificationLiveData.value = result
                }
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}