package com.monique.projetointegrador.features.popularmovies.presentation

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
import com.monique.projetointegrador.features.popularmovies.domain.GetMoviesByGenreUseCase
import com.monique.projetointegrador.features.popularmovies.domain.GetMoviesByGenreUseCaseImpl
import com.monique.projetointegrador.features.popularmovies.domain.GetPopularMoviesUseCase
import com.monique.projetointegrador.features.popularmovies.domain.GetPopularMoviesUseCaseImpl
import com.monique.projetointegrador.presentation.model.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class PopularMoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val favoriteMoviesUseCase: FavoriteMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val movieListLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData : LiveData<ViewState> = _viewStateLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData

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

    fun getMoviesByGenre(genresId: List<Int>) {
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

    fun addToFavorites(movie: Movie){
        viewModelScope.launch {
            favoriteMoviesUseCase.addFavoriteMovie(movie)
                .flowOn(dispatcher)
                .catch { error ->
                    print(error.message)
                }
                .collect {
                    //_favoriteMoviesLiveData.value = it todo(testar favoritar filme popular e ver se aparece na aba de favoritos)
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
                    val result = _moviesLiveData.value?.find { movie ->
                        movie.id == movieToRemove.id
                    }
                    result?.let { movie ->
                        movie.isFavorite = false
                    }
                    //todo(testar desfavoritar filme popular e ver se coracao fica despreenchido e some da aba de favoritos)
                }
        }
    }

}