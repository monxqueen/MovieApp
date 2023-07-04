package com.monique.projetointegrador.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.domain.usecase.*
import com.monique.projetointegrador.presentation.model.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class MoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val favoriteMoviesUseCase: FavoriteMoviesUseCase,
    private val searchForMoviesUseCase: SearchForMovieUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val movieListLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val favoriteMoviesLiveData : LiveData<List<Movie>> = _favoriteMoviesLiveData

    private val _searchResultsLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val searchResultsLiveData : LiveData<List<Movie>> = _searchResultsLiveData

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData : LiveData<ViewState> = _viewStateLiveData

    private val disposable = CompositeDisposable()

    fun getPopularMovies(){
        viewModelScope.launch {
            getPopularMoviesUseCase.execute()
                .flowOn(dispatcher)
                .catch { error ->
                    Log.e("ErroReq", "erro: " + error.cause)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
                .collect { result ->
                    _moviesLiveData.value = result
                }
        }
    }

    fun getMoviesByGenre(genresId: List<Int>){
        viewModelScope.launch {
            getMoviesByGenreUseCase.executeMoviesByGenre(genresId.joinToString(","))
                .flowOn(dispatcher)
                .catch { error ->
                    Log.e("ErroReq", "erro: " + error.cause)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
                .collect { result ->
                    _moviesLiveData.value = result
                }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase.executeGenres()
                .flowOn(dispatcher)
                .catch { error ->
                    Log.e("ErroReq", "erro: " + error.cause)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
                .collect { result ->
                    _genresLiveData.value = result
                }
        }
    }

    fun getFavoriteMovies() {
        viewModelScope.launch {
            favoriteMoviesUseCase.getFavoriteMovies()
                .flowOn(dispatcher)
                .catch { error ->
                    print(error.message)
                }
                .collect {
                    _favoriteMoviesLiveData.value = it
                }
        }
    }

    fun addToFavorites(movie: Movie){
        viewModelScope.launch {
            favoriteMoviesUseCase.addFavoriteMovie(movie)
                .flowOn(dispatcher)
                .catch { error ->
                    print(error.message)
                }
                .collect {
                    _favoriteMoviesLiveData.value = it
                }
        }
    }

    fun removeFromFavorites(movieToRemove: Movie){
        viewModelScope.launch {
            favoriteMoviesUseCase.removeFavoriteMovie(movieToRemove)
                .flowOn(dispatcher)
                .catch { error ->
                    print(error.message)
                }
                .collect {
                    _favoriteMoviesLiveData.value = it
                    val result = _moviesLiveData.value?.find { movie ->
                        movie.id == movieToRemove.id
                    }
                    result?.let { movie ->
                        movie.isFavorite = false
                    }
                }
        }
    }

    fun searchForMovie(movieSearched: Uri){
        viewModelScope.launch {
            searchForMoviesUseCase.executeSearch(movieSearched)
                .flowOn(dispatcher)
                .catch { error ->
                    Log.e("ErrorSearch", "Mensagem do erro: " + error.message)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
                .collect {
                    _searchResultsLiveData.value = it
                    if (it.isEmpty()) {
                        _viewStateLiveData.value = ViewState.MovieNotFound
                    }
                }
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}