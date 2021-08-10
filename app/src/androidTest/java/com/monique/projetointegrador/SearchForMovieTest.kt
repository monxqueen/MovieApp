package com.monique.projetointegrador

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.monique.projetointegrador.presentation.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchForMovieTest {
    @get:Rule
    val homeActivity = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun search_for_a_movie(){
            onView(ViewMatchers.withId(R.id.searchMovie)).perform(
            typeText("Moana"),
            pressImeActionButton(),
        )
    }
}