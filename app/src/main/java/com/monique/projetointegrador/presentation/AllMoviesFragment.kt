package com.monique.projetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Genres
import com.monique.projetointegrador.data.model.Movies
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter

class AllMoviesFragment : Fragment(), MovieListener {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var progressBar: ProgressBar
    private val viewModel = AllMoviesFragmentViewModel()

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

        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)

        progressBar = view.findViewById(R.id.loading)

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)

        rvMovies.adapter = moviesAdapter
        rvGenres.adapter = genresAdapter

        viewModel.getMovies()
        viewModel.getGenres()

        getGenresToShow()
        getMoviesToShow()
    }

    private fun getMoviesToShow(){
        viewModel.moviesLiveData.observe(viewLifecycleOwner, { response ->
            response?.let{
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }

        })
    }

    private fun getGenresToShow(){
        viewModel.genresLiveData.observe(viewLifecycleOwner,{ response ->
            response?.let{
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun openMovieDetails(movie: Movies){
        val intent = Intent(requireContext(), MovieInfoActivity::class.java)
        intent.putExtra("MOVIE_INFO", movie)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genresId: List<Int>) {
        viewModel.getMoviesByGenre(genresId)
        getMoviesToShow()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllMoviesFragment()
    }
    /*rvGenres.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)*/

    //scroll das recyclerviews estavam muito bugados quando comecei a utilizar o viewpager2, então essa função pode me ajudar a consertar o problema:
    /*val listener = object : RecyclerView.OnItemTouchListener {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val action = e.action
            if (rvMovies.canScrollHorizontally(RecyclerView.FOCUS_FORWARD)) {
                when (action) {
                    MotionEvent.ACTION_MOVE -> rv.parent
                        .requestDisallowInterceptTouchEvent(true)
                }
                return false
            }
            else {
                when (action) {
                    MotionEvent.ACTION_MOVE -> rv.parent
                        .requestDisallowInterceptTouchEvent(false)
                }
                rvMovies.removeOnItemTouchListener(this)
                rvGenres.removeOnItemTouchListener(this)
                return true
            }
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    }
    rvGenres.addOnItemTouchListener(listener)
    rvMovies.addOnItemTouchListener(listener)*/
}