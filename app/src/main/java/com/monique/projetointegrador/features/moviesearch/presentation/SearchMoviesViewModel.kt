package com.monique.projetointegrador.features.moviesearch.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.features.favoritemovies.domain.FavoriteMoviesUseCase
import com.monique.projetointegrador.domain.usecase.GetGenresUseCase
import com.monique.projetointegrador.features.moviesearch.domain.SearchForMovieUseCase
import com.monique.projetointegrador.presentation.model.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchMoviesViewModel(
    private val searchForMoviesUseCase: SearchForMovieUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val favoriteMoviesUseCase: FavoriteMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _searchResultsLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val searchResultsLiveData : LiveData<List<Movie>> = _searchResultsLiveData

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData : LiveData<ViewState> = _viewStateLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

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

    fun addToFavorites(movie: Movie){
        viewModelScope.launch {
            favoriteMoviesUseCase.addFavoriteMovie(movie)
                .flowOn(dispatcher)
                .catch { error ->
                    print(error.message)
                }
                .collect {
                    //_favoriteMoviesLiveData.value = it
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
                    //_favoriteMoviesLiveData.value = it
                    /*val result = _moviesLiveData.value?.find { movie ->
                        movie.id == movieToRemove.id
                    }
                    result?.let { movie ->
                        movie.isFavorite = false
                    }*/
                }
        }
    }
}