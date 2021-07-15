package com.monique.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.monique.projetointegrador.R
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var searchEdtTxt: EditText
    private lateinit var searchBtn: ImageButton
    private lateinit var tbLytOptions: TabLayout
    private lateinit var viewPager: ViewPager2

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
        //viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        //tbLytOptions.setupWithViewPager(viewPager)
        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tbLytOptions, viewPager){ tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

    }

    private fun getTabTitle(position: Int): String{
        return when (position){
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }

}