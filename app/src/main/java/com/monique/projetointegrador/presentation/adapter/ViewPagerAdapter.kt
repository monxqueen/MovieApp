package com.monique.projetointegrador.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.monique.projetointegrador.presentation.popularmovies.PopularMoviesFragment
import com.monique.projetointegrador.presentation.favoritemovies.FavoriteMoviesFragment

class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            ALL_MOVIES_POSITION -> PopularMoviesFragment()
            FAVORITE_MOVIES_POSITION -> FavoriteMoviesFragment()
            else -> PopularMoviesFragment()
        }
    }

    companion object {
        const val ALL_MOVIES_POSITION = 0
        const val FAVORITE_MOVIES_POSITION = 1
    }
}