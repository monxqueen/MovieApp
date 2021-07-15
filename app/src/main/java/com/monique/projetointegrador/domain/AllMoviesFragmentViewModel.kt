package com.monique.projetointegrador.domain

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monique.projetointegrador.data.model.Movies
import com.monique.projetointegrador.data.repository.Network
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllMoviesFragmentViewModel(): ViewModel() {

    private lateinit var moviesLiveData: MutableLiveData<List<Movies>>
    fun getPopularMovies(){
        Network.getService().getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            /*.doOnComplete {
                progressBar.visibility = View.GONE
            }*/
            .subscribe { response ->

            }
        //dps q carregar a req da api, colocar o progress bar como gone: loading.visibility = View.GONE
    }
}