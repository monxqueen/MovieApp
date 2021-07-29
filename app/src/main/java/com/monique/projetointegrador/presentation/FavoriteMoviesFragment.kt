package com.monique.projetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter

class FavoriteMoviesFragment : Fragment(), MovieListener { /*se for utilizar a interface de fav movies, coloca FavMoviesListListener como parâmetro aqui e recebe a lista de filmes fav da fragment all movies*/

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var rvGenres: RecyclerView
    private lateinit var rvMovies: RecyclerView
    //private val viewModelFavorites = FavoriteMoviesViewModel()
    private lateinit var viewModelFavorites: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_movies, container,  false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvGenres = view.findViewById(R.id.rvGenres)
        rvMovies = view.findViewById(R.id.rvMovies)

        progressBar = view.findViewById(R.id.loading)

        viewModelFavorites = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        rvGenres.adapter = genresAdapter
        rvMovies.adapter = moviesAdapter

        viewModelFavorites.getGenres()
        observeGenres()
        viewModelFavorites.getFavoriteMovies()
        observeFavoriteMovies()
    }

    private fun observeGenres(){
        viewModelFavorites.genreListLiveData.observe(viewLifecycleOwner, { response ->
            response?.let{
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        })
    }

    /*private fun observeFavoriteMovies(){
        viewModelFavorites.movieListLiveData.observe(viewLifecycleOwner, { result ->
            result?.let{
                it.forEach { it.isFavorite = true }
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }

        })
    }*/

    private fun observeFavoriteMovies(){
        viewModelFavorites.favoriteMoviesLiveData.observe(viewLifecycleOwner, { result ->
            result?.let{
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }

        })
    }

    /*override fun saveMoviesToFavoriteTab(movie: Movie, addOrRemove: String) {
        if(addOrRemove == "remove"){
            viewModelFavorites.unfavoriteMovie(movie)
        }
    }*/

    override fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean) {
        if(!isChecked){
            viewModelFavorites.unfavoriteMovie(movie)
        }
    }



    override fun openMovieDetails(movieId: Int){
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE_ID", movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genresId: List<Int>) {
        /*viewModel.getMoviesByGenre(genresId)
        getMoviesToShow()*/
    }

    companion object {
        var favMoviesList = mutableListOf<Movie>()
    }

}