package com.monique.projetointegrador.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.monique.projetointegrador.R
import com.monique.projetointegrador.databinding.ActivityHomeBinding
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter.Companion.ALL_MOVIES_POSITION
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter.Companion.FAVORITE_MOVIES_POSITION
import com.monique.projetointegrador.features.moviesearch.presentation.SearchMoviesFragment


internal class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    private var searchMovieEditText: EditText? = null
    private lateinit var searchBtn: ImageButton
    private lateinit var greenIcon: ImageView
    private lateinit var searchModeTxt: TextView
    private lateinit var backToHomeBtn: TextView
    private lateinit var tabLytOptions: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var movieSearched: String
    private var searchFragment: SearchMoviesFragment? = null
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
        setupSearchEditText()
        setupButtonListener()
    }

    private fun setupViewPager() {
        searchMovieEditText = findViewById(R.id.searchMovieEditText)
        searchBtn = findViewById(R.id.submitSearch)
        greenIcon = findViewById(R.id.greenIcon)
        searchModeTxt = findViewById(R.id.searchModeTxt)
        backToHomeBtn = findViewById(R.id.backToHomeBtn)
        fragmentContainer = findViewById(R.id.searchFragmentContainer)
        tabLytOptions = findViewById<TabLayout>(R.id.tabLytOptions)
        viewPager = findViewById<ViewPager2>(R.id.viewPager)

        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false
        tabLytOptions.visibility = View.VISIBLE
        viewPager.visibility = View.VISIBLE
        TabLayoutMediator(tabLytOptions, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun setupSearchEditText() {
       // with(binding) {
            searchMovieEditText?.setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        movieSearched = searchMovieEditText?.text.toString()
                        if (searchFragment == null) {
                            searchFragment = SearchMoviesFragment.newInstance(movieSearched)
                            searchFragment?.let {
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.searchFragmentContainer, it)
                                    .commit()
                            }
                        } else {
                            searchFragment?.updateQuery(movieSearched.toUri())
                        }
                        true
                    }
                    else -> false
                }
            }

            searchMovieEditText?.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    visibilitySearchMode()
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null) {
                        if (s.isEmpty()) {
                            viewPager.setCurrentItem(ALL_MOVIES_POSITION, false)
                            visibilityNotSearchMode()
                        }
                    }
                }
            })
        //}
    }

    private fun setupButtonListener() {
        backToHomeBtn.setOnClickListener {
            visibilityNotSearchMode()
            searchMovieEditText?.text?.clear()
        }

    }

    private fun getTabTitle(position: Int): String{
        return when (position){
            ALL_MOVIES_POSITION -> "Filmes"
            FAVORITE_MOVIES_POSITION -> "Favoritos"
            else -> ""
        }
    }

    private fun visibilitySearchMode() {
        //with(binding) {
            tabLytOptions.visibility = View.GONE
            viewPager.visibility = View.GONE
            greenIcon.visibility = View.VISIBLE
            searchModeTxt.visibility = View.VISIBLE
            backToHomeBtn.visibility = View.VISIBLE
            fragmentContainer.visibility = View.VISIBLE
        //}
    }

    private fun visibilityNotSearchMode() {
        //with(binding) {
            tabLytOptions.visibility = View.VISIBLE
            viewPager.visibility = View.VISIBLE
            fragmentContainer.visibility = View.GONE
            greenIcon.visibility = View.GONE
            searchModeTxt.visibility = View.GONE
            backToHomeBtn.visibility = View.GONE
        //}
    }

}