package com.monique.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.monique.projetointegrador.R
import com.monique.projetointegrador.presentation.adapter.ViewPagerAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var searchEdtTxt: EditText
    private lateinit var searchBtn: ImageButton
    private lateinit var tbLytOptions: TabLayout
    private lateinit var viewPager: ViewPager

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

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        tbLytOptions.setupWithViewPager(viewPager)

    }

}