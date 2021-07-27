package com.monique.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.monique.projetointegrador.R
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var searchEdtTxt: EditText
    private lateinit var searchBtn: ImageButton
    private lateinit var greenIcon: ImageView
    private lateinit var searchModeTxt: TextView
    private lateinit var backToHomeBtn: TextView
    private lateinit var tbLytOptions: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var genresRv: RecyclerView
    private lateinit var moviesRv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var movieRvAdapter: MoviesRvAdapter
    private lateinit var genresRvAdapter: GenresRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindviews()
    }

    private fun bindviews(){
        searchEdtTxt = findViewById(R.id.searchMovie)
        searchBtn = findViewById(R.id.submitSearch)
        tbLytOptions = findViewById(R.id.tabLytOptions)
        viewPager = findViewById(R.id.viewPager)
        greenIcon = findViewById(R.id.greenIcon)
        searchModeTxt = findViewById(R.id.searchModeTxt)
        backToHomeBtn = findViewById(R.id.backToHomeBtn)
        genresRv = findViewById(R.id.rvGenresHome)
        moviesRv = findViewById(R.id.rvMoviesHome)
        progressBar = findViewById(R.id.loadingHome)

        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tbLytOptions, viewPager){ tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        searchEdtTxt.setOnClickListener {
            tbLytOptions.visibility = View.INVISIBLE
            viewPager.visibility = View.INVISIBLE
            greenIcon.visibility = View.VISIBLE
            searchModeTxt.visibility = View.VISIBLE
            backToHomeBtn.visibility = View.VISIBLE

            val movieSearched = searchEdtTxt.text.toString()

        }

    }

    private fun getTabTitle(position: Int): String{
        return when (position){
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }

    fun getViewPager(): ViewPager2{
        return viewPager
    }

}