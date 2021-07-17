package com.monique.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Constants
import com.monique.projetointegrador.data.model.Movies

class MovieInfoActivity : AppCompatActivity() {

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

        val movie = intent.getParcelableExtra("MOVIE_INFO") as Movies?

        movie?.let{
            Glide.with(this).load(Constants.BASE_URL_IMAGE.value + it.getImg()).into(posterMovie)
            movieTitle.text = it.getTitle()
            movieRating.text = it.showRating()
            movieSynopsis.text = it.getOverview()
        }




    }

}
