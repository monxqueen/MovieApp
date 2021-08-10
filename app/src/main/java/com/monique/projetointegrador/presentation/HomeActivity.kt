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
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter

const val ALL_MOVIES_POSITION = 0
const val FAVORITE_MOVIES_POSITION = 1
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
    private var searchFragment: SearchMoviesFragment? = null

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

        searchEdtTxt?.setOnEditorActionListener { _, actionId, _ ->
            when(actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    movieSearched = searchEdtTxt?.text.toString()
                    if(searchFragment == null) {
                        searchFragment = SearchMoviesFragment.newInstance(movieSearched)
                        searchFragment?.let{
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.searchFragmentContainer, it)
                                .commit()
                        }
                    }
                    else {
                        searchFragment?.updateQuery(movieSearched.toUri())
                    }
                    true}
                else -> false
            }
        }

        searchEdtTxt?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tbLytOptions.visibility = View.GONE
                viewPager.visibility = View.GONE
                greenIcon.visibility = View.VISIBLE
                searchModeTxt.visibility = View.VISIBLE
                backToHomeBtn.visibility = View.VISIBLE
                fragmentContainer.visibility = View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isEmpty()) {
                        viewPager.setCurrentItem(ViewPagerAdapter.ALL_MOVIES_POSITION, false)
                        tbLytOptions.visibility = View.VISIBLE
                        viewPager.visibility = View.VISIBLE
                        fragmentContainer.visibility = View.GONE
                        greenIcon.visibility = View.GONE
                        searchModeTxt.visibility = View.GONE
                        backToHomeBtn.visibility = View.GONE
                    }
                }
            }
        })

        backToHomeBtn.setOnClickListener {
            tbLytOptions.visibility = View.VISIBLE
            viewPager.visibility = View.VISIBLE
            fragmentContainer.visibility = View.GONE
            greenIcon.visibility = View.GONE
            searchModeTxt.visibility = View.GONE
            backToHomeBtn.visibility = View.GONE
            searchEdtTxt?.text?.clear()
        }
    }

    private fun getTabTitle(position: Int): String{
        return when (position){
            ALL_MOVIES_POSITION -> "Todos os filmes"
            FAVORITE_MOVIES_POSITION -> "Favoritos"
            else -> ""
        }
    }

}