package com.monique.projetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter

class FavoriteMoviesFragment : Fragment(), MovieListener { /*se for utilizar a interface de fav movies, coloca FavMoviesListListener como parâmetro aqui e recebe a lista de filmes fav da fragment all movies*/

    private lateinit var moviesAdapter: MoviesRvAdapter
    //private var favMoviesList: MutableList<Movie> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var rvGenres: RecyclerView
    private lateinit var rvMovies: RecyclerView
    //private lateinit var db: AppDatabase
    private val viewModel = FavoriteMoviesFragmentViewModel()

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


        rvGenres = view.findViewById(R.id.rvGenres)
        rvMovies = view.findViewById(R.id.rvMovies)

        /*val favoriteMoviesDao = db.favoriteMoviesDao()
        favMoviesList = favoriteMoviesDao.loadFavoriteMovies(true)*/

        progressBar = view.findViewById(R.id.loading)
        if(favMoviesList.isNotEmpty()){
            progressBar.visibility = View.GONE
        }

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this, favMoviesList)
        rvGenres.adapter = genresAdapter
        rvMovies.adapter = moviesAdapter

        viewModel.getGenres()
        getGenresToShow()
    }

    private fun getGenresToShow(){
        viewModel.genreListLiveData.observe(viewLifecycleOwner,{ response ->
            response?.let{
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun saveMoviesToFavoriteTab(movie: Movie, addOrRemove: String) {
        if(addOrRemove == "remove"){
            favMoviesList.remove(movie)
            moviesAdapter.dataset.clear()
            moviesAdapter.dataset.addAll(favMoviesList)
            moviesAdapter.notifyDataSetChanged()
        }
    }

    override fun openMovieDetails(movieId: Int){
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE_ID", movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genresId: List<Int>) {
        viewModel.getMoviesByGenre(genresId)
        getMoviesToShow()
    }

    private fun getMoviesToShow(){
        viewModel.movieListLiveData.observe(viewLifecycleOwner, { response ->
            response?.let{
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
            }

        })
    }

    companion object {
        var favMoviesList = mutableListOf<Movie>()
    }

}