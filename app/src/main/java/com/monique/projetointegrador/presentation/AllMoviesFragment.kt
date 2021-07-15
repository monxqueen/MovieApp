package com.monique.projetointegrador.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Genres
import com.monique.projetointegrador.data.model.Movies
import com.monique.projetointegrador.data.repository.Network
import com.monique.projetointegrador.domain.AllMoviesFragmentViewModel
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllMoviesFragment : Fragment() { /*se for utilizar a interface de fav movies, coloca FavMoviesListListener como parâmetro aqui*/

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var progressBar: ProgressBar
    var favMoviesList: MutableList<Movies> = mutableListOf()
    private var viewModel = AllMoviesFragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.loading)

        val genresList: MutableList<Genres> = mutableListOf()
        val moviesList: MutableList<Movies> = mutableListOf()

        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)

        genresAdapter = GenresRvAdapter(view.context, genresList)
        moviesAdapter = MoviesRvAdapter(context = view.context, dataset = moviesList)

        rvMovies.adapter = moviesAdapter
        rvGenres.adapter = genresAdapter

        rvGenres.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        getGenres()
    }

    @SuppressLint("CheckResult")
    fun getGenres(){
        Network.getService().getAllGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe { response ->
                genresAdapter.dataset.addAll(response.genres)
                genresAdapter.notifyDataSetChanged()
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllMoviesFragment()
    }

    /*override fun addToFavorite(element: Movies){
        favMoviesList.add(element)
        Toast.makeText(requireContext(), "Filme adicionado aos favoritos", Toast.LENGTH_LONG).show()
    }

    override fun removeFromFavorite(position: Int){
        favMoviesList.removeAt(position)
    }

    override fun elementIsFavorite(element: Movies): Boolean{
        return favMoviesList.contains(element)
    }*/
}