package com.monique.projetointegrador.domain.model

import com.monique.projetointegrador.domain.Genre
import com.monique.projetointegrador.domain.MovieDetail
import org.junit.Assert
import org.junit.Test

class MovieDetailTest {

    @Test
    fun `when the user decides to see movie details, that movie's rating should be displayed as a 0-100 percentage`(){
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            1,
            "",
            "2001-09-13",
            null,
            8f,
            "Barbie",
        )

        val result = movieDetailed.getRating()

        Assert.assertEquals("80%", result)
    }

    @Test
    fun `when the user decides to see movie details, that movie's release date should only display the year it was released`(){
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            2,
            "",
            "2001-09-13",
            null,
            8f,
            "Lilo & Stitch",
        )

        val result = movieDetailed.getReleaseYear()

        Assert.assertEquals("2001", result)
    }

    @Test
    fun `when the user decides to see movie details and its runtime is more than an hour, it should be displayed with hours separated from minutes`(){
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            3,
            "",
            "2001-09-13",
            113,
            8f,
            "Moana",
        )

        val result = movieDetailed.getRuntime()

        Assert.assertEquals("1h 53min", result)
    }

    @Test
    fun `when the user decides to see movie details and its runtime is less than an hour, it should be displayed only in minutes`(){
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            3,
            "",
            "2001-09-13",
            45,
            8f,
            "Interstellar",
        )

        val result = movieDetailed.getRuntime()

        Assert.assertEquals("45min", result)
    }

    @Test
    fun `when user decides to see movie details and its runtime is null, an empty string should be displayed`(){
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            3,
            "",
            "2001-09-13",
            null,
            8f,
            "Shrek",
        )

        val result = movieDetailed.getRuntime()

        Assert.assertEquals("", result)
    }
}