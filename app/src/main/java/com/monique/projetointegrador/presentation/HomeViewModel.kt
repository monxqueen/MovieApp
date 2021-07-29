package com.monique.projetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.data.repository.MoviesRepositoryImpl
import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.Movie

class HomeViewModel: ViewModel() {

    private val moviesRepositoryImpl = MoviesRepositoryImpl()

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData : LiveData<List<Genre>> = _genresLiveData




}