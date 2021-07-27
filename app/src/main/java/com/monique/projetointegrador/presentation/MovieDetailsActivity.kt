package com.monique.projetointegrador.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.domain.MovieDetail
import com.monique.projetointegrador.presentation.adapter.CastRvAdapter
import com.monique.projetointegrador.presentation.adapter.MovieDetailsGenresRvAdapter

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var returnBtn: FloatingActionButton
    private lateinit var posterMovie: ShapeableImageView
    private lateinit var movieRating: TextView
    private lateinit var movieTitle: TextView
    private lateinit var movieYear: TextView
    private lateinit var ageRestriction: TextView
    private lateinit var movieDuration: TextView
    private lateinit var favButton: ToggleButton
    private lateinit var genresRv: RecyclerView
    private lateinit var movieSynopsis: TextView
    private lateinit var castRv: RecyclerView
    private lateinit var castRvAdapter: CastRvAdapter
    private lateinit var genresRvAdapter: MovieDetailsGenresRvAdapter
    private val viewModel = MovieDetailsActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_info)
        posterMovie = findViewById(R.id.posterMovie)
        movieTitle = findViewById(R.id.movieTitle)
        movieRating = findViewById(R.id.ratingMovieInfoAct)
        movieYear = findViewById(R.id.movieYear)
        ageRestriction = findViewById(R.id.ageRestriction)
        movieDuration = findViewById(R.id.movieDuration)
        favButton = findViewById(R.id.favBtnMovieInfo)
        genresRv = findViewById(R.id.genreRvMovieInfo)
        movieSynopsis = findViewById(R.id.movieSynopsis)
        castRv = findViewById(R.id.castRv)
        returnBtn = findViewById(R.id.returnBtn)

        val movieId = intent.extras?.getInt("MOVIE_ID")

        viewModel.getMovieDetails(movieId!!)
        getMovieDetails()

        returnBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getMovieDetails(){
        viewModel.movieLiveData.observe(this, { response ->
            response?.let{
                showMovie(it)
            }
        })
    }

    private fun showMovie(movie: MovieDetail){
        genresRvAdapter = MovieDetailsGenresRvAdapter(movie.genres)
        genresRv.adapter = genresRvAdapter

        castRvAdapter = CastRvAdapter(this)
        castRv.adapter = castRvAdapter
        viewModel.getCast(movie.id)
        getCastOfMovie()

        movie.backdrop_path?.let{
            Glide.with(this).load(Constants.BASE_URL_IMAGE.value + movie.backdrop_path).into(posterMovie)
        }
        movie.overview?.let{
            movieSynopsis.text = it
        }
        movie.runtime?.let{
            movieDuration.text = timeConversion(it)
        }

        movieTitle.text = movie.title
        movieRating.text = ratingConversion(movie.vote_average)
        movieYear.text = movie.release_date.take(4)


        //FALTA: CERTIFICATION
    }

    private fun getCastOfMovie(){
        viewModel.castLiveData.observe(this, { response ->
            response?.let{
                castRvAdapter.dataset.addAll(it)
                castRvAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun timeConversion(runtime: Int): String{
        val time = runtime/60
        val onlyHours = time.toString().substringBefore(".").toInt()
        val onlyMinutes = runtime - (onlyHours * 60)
        if(onlyHours == 0){
            return onlyMinutes.toString()+"min"
        }else{
            return onlyHours.toString()+"h "+onlyMinutes.toString()+"min"
        }
    }

    private fun ratingConversion(vote_average: Float): String{
        val rating = (vote_average*10).toInt().toString()
        return "$rating%"
    }


}
