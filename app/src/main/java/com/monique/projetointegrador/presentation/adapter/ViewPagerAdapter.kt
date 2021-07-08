package com.monique.projetointegrador.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.monique.projetointegrador.presentation.AllMoviesFragment
import com.monique.projetointegrador.presentation.FavoriteMoviesFragment

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = AllMoviesFragment()
        when(position){
            0 -> fragment = AllMoviesFragment()
            1 -> fragment = FavoriteMoviesFragment()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var tabLayoutText = ""
        when(position){
            0 -> tabLayoutText = "Todos os filmes"
            1 -> tabLayoutText = "Favoritos"
        }
        return tabLayoutText
    }
}