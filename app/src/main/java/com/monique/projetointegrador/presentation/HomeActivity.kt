package com.monique.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.monique.projetointegrador.R
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var searchEdtTxt: EditText? = null
    private lateinit var searchBtn: ImageButton
    private lateinit var greenIcon: ImageView
    private lateinit var searchModeTxt: TextView
    private lateinit var backToHomeBtn: TextView
    private lateinit var tbLytOptions: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var movieSearched: String

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
        fragmentContainer = findViewById(R.id.searchFragmentContainer)

        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false
        TabLayoutMediator(tbLytOptions, viewPager){ tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        searchEdtTxt?.setOnClickListener {
            tbLytOptions.visibility = View.INVISIBLE
            viewPager.visibility = View.INVISIBLE
            greenIcon.visibility = View.VISIBLE
            searchModeTxt.visibility = View.VISIBLE
            backToHomeBtn.visibility = View.VISIBLE

            movieSearched = searchEdtTxt?.text.toString()
            val fragment = SearchMoviesFragment.newInstance(movieSearched)

            supportFragmentManager.beginTransaction()
                .add(R.id.searchFragmentContainer, fragment)
                .addToBackStack(null)
                .commit()

        }

        backToHomeBtn.setOnClickListener {
            fragmentContainer.visibility = View.INVISIBLE
            tbLytOptions.visibility = View.VISIBLE
            viewPager.visibility = View.VISIBLE
            greenIcon.visibility = View.INVISIBLE
            searchModeTxt.visibility = View.INVISIBLE
            backToHomeBtn.visibility = View.INVISIBLE
            searchEdtTxt?.text?.clear()
        }
    }

    private fun getTabTitle(position: Int): String{
        return when (position){
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }

}