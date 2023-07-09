package com.monique.projetointegrador.features.favoritemovies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.domain.usecase.GetGenresUseCase
import com.monique.projetointegrador.features.favoritemovies.domain.FavoriteMoviesUseCase
import com.monique.projetointegrador.features.favoritemovies.domain.FavoriteMoviesUseCaseImpl
import com.monique.projetointegrador.presentation.model.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class FavoriteMoviesViewModel(
    private val favoriteMoviesUseCase: FavoriteMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val favoriteMoviesLiveData : LiveData<List<Movie>> = _favoriteMoviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData : LiveData<ViewState> = _viewStateLiveData

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

    fun removeFromFavorites(movieToRemove: Movie){
        viewModelScope.launch {
            favoriteMoviesUseCase.removeFavoriteMovie(movieToRemove)
                .flowOn(dispatcher)
                .catch { error ->
                    print(error.message)
                }
                .collect {
                    _favoriteMoviesLiveData.value = it
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